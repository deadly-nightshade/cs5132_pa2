package me.squiddy.pa2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class PatientController {
    // initialise scene to show patient info

    //parent controller
    private MainController parentController;

    private Patient patient;

    @FXML
    TextField NameField;

    @FXML
    Spinner<Integer> AgeSelect;

    @FXML
    DatePicker DateAddedPicker;

    @FXML
    DatePicker DeathDatePicker;

    @FXML
    public void initialize(){
        AgeSelect.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 130));
    }
    // save function, save to patient
    @FXML
    void savePatient() {
        //error checking
        String msg = "";
        boolean invalid = false;
        if (NameField.getText().isEmpty()) msg = "Name";
        if (AgeSelect.getValue() == null) msg = "Age";
        try {
            Integer.parseInt(AgeSelect.getEditor().getText()); //check if int
        }
        catch(NumberFormatException e){msg = "age"; invalid = true;}
        try{
            DateAddedPicker.getConverter().fromString(DateAddedPicker.getEditor().getText());
            if (DateAddedPicker.getValue() == null) msg = "Date added";
        }
        catch(Exception e){msg = "date added"; invalid = true;}
        try {
            DeathDatePicker.getConverter().fromString(DeathDatePicker.getEditor().getText());
            if (DeathDatePicker.getValue() == null) msg = "Estimated date of death";
        }
        catch(Exception e){msg = "estimated date of death"; invalid = true;}

        //error displaying
        if(msg.isEmpty()){
            if(DeathDatePicker.getValue().isBefore(DateAddedPicker.getValue())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Death date is before patient added!");
                alert.showAndWait();
                return;
            }
            if(DateAddedPicker.getValue().isAfter(LocalDate.now())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Registration date is after today's date!");
                alert.showAndWait();
                return;
            }
            if(DeathDatePicker.getValue().isBefore(LocalDate.now())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Death date is before today's date!");
                alert.showAndWait();
                return;
            }

            patient = new Patient(NameField.getText(), AgeSelect.getValue(), DateAddedPicker.getValue(), DeathDatePicker.getValue());
            parentController.addToPQ(patient, patient.calculatePriority());
            Stage stage = (Stage) NameField.getScene().getWindow();
            stage.close();
        }
        else if(invalid){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid " + msg + " input!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(msg + " cannot be empty!");
            alert.showAndWait();
        }
    }

    void setParentController(MainController c){
        parentController = c;
    }
}
