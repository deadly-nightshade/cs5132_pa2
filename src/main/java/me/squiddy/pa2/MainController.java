package me.squiddy.pa2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    // initialise javafx things

    void updateTable() {
        // call update table every time it loads???
    }

    @FXML
    void addPatient() throws IOException {
        // go to patient page
        // update table
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add patient");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editPatient() {
        // go to patient page
        // update table
    }

    @FXML
    void deletePatient() {
        // delete
        // update table
    }
}