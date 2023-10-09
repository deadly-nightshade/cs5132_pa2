package me.squiddy.pa2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    // initialise main scene

    @FXML
    public TableView table;

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