package me.squiddy.pa2;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainController {
    // initialise main scene

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

    @FXML
    void updateTable() {
        // updates the table
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        info.setCellValueFactory(new PropertyValueFactory<>("info"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        table.itemsProperty().bind(patientListProperty);
        patientList.add(new PatientEntry(1, new Patient("Ayden", 17, LocalDate.now().minusDays(5), LocalDate.now().plusDays(2))));
        patientList.add(new PatientEntry(1, new Patient("Kabir", 18, LocalDate.now().minusDays(4), LocalDate.now().plusDays(1))));
        System.out.println(table.getItems());
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
        stage.show();
    }

    @FXML
    void dequeuePatient() {
        // dequeues a patient and updates table
    }
}