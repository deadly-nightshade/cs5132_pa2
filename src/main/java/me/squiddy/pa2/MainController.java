package me.squiddy.pa2;

import PQueue.PQueueViewable;
import PQueue.Pair;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainController {
    static String fileName = "";
    // initialise main scene
    private PQueueViewable<Patient, Double> pq;

    private PatientController childController;

    @FXML
    public TableView<PatientEntry> table;
    @FXML
    public TableColumn<PatientEntry, Integer> rank;
    @FXML
    public TableColumn<PatientEntry, String> info;
    @FXML
    public TableColumn<PatientEntry, Double> priority;
    private ObservableList<PatientEntry> patientList;
    private Property<ObservableList<PatientEntry>> patientListProperty;

    @FXML
    public void initialize() {
        patientList = FXCollections.observableArrayList();
        patientListProperty = new SimpleObjectProperty<>(patientList);
        table.itemsProperty().bind(patientListProperty);
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        info.setCellValueFactory(new PropertyValueFactory<>("info"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    PatientEntry entry = table.getSelectionModel().getSelectedItem();
                    String[] arr = entry.getInfo().split(": ");
                    String name = arr[1].substring(0, arr[1].length()-5);
                    String age = arr[2].substring(0, arr[2].length()-19);
                    String dateAdded = arr[3].substring(0, arr[3].length()-12);
                    String dateDeath = arr[4];
                    String message = "Name: " + name + "\nAge: " + age + "\nRegistration Date: " + dateAdded + "\nDeath Date: " + dateDeath;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Patient info");
                    alert.setHeaderText(message);
                    alert.showAndWait();
                }
            }
        });
    }

    void initData(){
        pq = new PQueueViewable<>();
    }

    @FXML
    void updateTable() {
        // updates the table
        // converts all elements in priority queue into patient entries
        ArrayList<Pair<Patient,Double>> list = pq.getQueue();
        patientList.clear();
        for(int i = 0; i < list.size(); i++){
            PatientEntry temp = new PatientEntry(i+1, list.get(i).getLeft(), list.get(i).getRight());
            patientList.add(temp);
        }
    }

    @FXML
    void addPatient() throws IOException {
        // goes to add patient page
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Patient");
        stage.setScene(scene);
        stage.setResizable(false);
        childController = fxmlLoader.getController();
        childController.setParentController(this);
        stage.show();
    }

    @FXML
    void dequeuePatient(){
        if(pq.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No patients left to dequeue!");
            alert.showAndWait();
        }
        else{
            Patient p = pq.dequeue().getLeft();
            String message = "Name: " + p.getName() + "\nAge: " + p.getAge() + "\nRegistration Date: " + p.getRegistrationDate() + "\nDeath Date: " + p.getDeathDate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient removed");
            alert.setHeaderText(message);
            alert.showAndWait();
            updateTable();
        }
    }

    void addToPQ(Patient p, double priority){
        pq.enqueue(p, priority);
        updateTable();
    }

    @FXML
    void openFile() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showOpenDialog(table.getScene().getWindow());
        fileName = file.getPath();
        System.out.println(fileName);

        // read file
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line;
            pq = new PQueueViewable<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // line format
                // name, age, regisDate, deathDate, priority
                String[] hold = line.split(",");
                LocalDate regisDate = LocalDate.parse(hold[2], formatter);
                LocalDate deathDate = LocalDate.parse(hold[3], formatter);

                Patient patient = new Patient(hold[0], Integer.parseInt(hold[1]), regisDate, deathDate);
                double priority = Double.parseDouble(hold[4]);
                pq.enqueue(patient, priority);
            }
            reader.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        updateTable();
    }

    @FXML
    void saveFile() {
        if (fileName == "") {
            // save as
            DirectoryChooser dir_chooser = new DirectoryChooser();

            File file = dir_chooser.showDialog(table.getScene().getWindow());
            fileName = file.getPath();

            // get filename
            TextInputDialog td = new TextInputDialog("Enter file name. Please don't try any funny stuff here.");
            td.showAndWait();
            fileName += "\\"+ td.getEditor().getText() + ".csv";

        }
            // just normal save
        System.out.println(fileName);

        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(priorityQueueToString());
            myWriter.close();
            System.out.println("success");
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    String priorityQueueToString() {
        String toWrite = "";

        ArrayList<Pair<Patient,Double>> list = pq.getQueue();

        for(int i = 0; i < list.size(); i++){
            Patient patient = list.get(i).getLeft();
            double priority = list.get(i).getRight();
            toWrite += patient.getName() + "," + patient.getAge() + "," + patient.getRegistrationDate() + "," + patient.getDeathDate() + "," + priority + "\n";

        }
        return toWrite;
    }

}