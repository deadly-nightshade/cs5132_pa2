package me.squiddy.pa2;

import PQueue.PQueueViewable;
import PQueue.Pair;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
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
    private ObservableList<PatientEntry> patientList = FXCollections.observableArrayList();
    private Property<ObservableList<PatientEntry>> patientListProperty = new SimpleObjectProperty<>(patientList);

    void initData(){
        pq = new PQueueViewable<>();
    }

    @FXML
    void updateTable() {
        // updates the table
        //converts all pq into patiententries
        ArrayList<Pair<Patient,Double>> list = pq.getQueue();
        patientList.clear();
        for(int i = 0; i < list.size(); i++){
            PatientEntry temp = new PatientEntry(i+1, list.get(i).getLeft(), list.get(i).getRight());
            patientList.add(temp);
        }
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        info.setCellValueFactory(new PropertyValueFactory<>("info"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        table.itemsProperty().bind(patientListProperty);
        table.refresh();
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
        //stage.setOnHiding(windowEvent -> updateTable());
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
}