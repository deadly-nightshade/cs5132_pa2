package me.squiddy.pa2;

import PQueue.PQueueViewable;
import PQueue.Pair;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    // initialise main scene
    private PQueueViewable<Patient, Integer> pq;

    @FXML
    public TableView table;

    void initData(){
        pq = new PQueueViewable<>();
        System.out.println("test");
    }

    @FXML
    void updateTable() {
        // updates the table
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
}