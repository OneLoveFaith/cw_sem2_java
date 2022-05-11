package com.pharmacy.Controllers;

//Imports
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddWorker {

    //FXML functional elements
    @FXML
    private Button HomeButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private TextField NameField;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasswordField;

    @FXML
    private ComboBox<String> PositionBox;

    @FXML
    private Button Submit;


    @FXML
    void initialize(){
        //Log out button action
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));

        //Home button action
        HomeButton.setOnAction(event -> Methods.loadStage(Login.position, event));

        //Filling position box
        PositionBox.getItems().add("Worker");
        PositionBox.getItems().add("Courier");

        //Adding worker
        Submit.setOnAction(event -> Methods.registration(NameField.getText(), LoginField.getText(), PasswordField.getText(), PositionBox.getValue()));
    }

}
