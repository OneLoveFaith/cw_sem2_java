package com.pharmacy.Controllers;

//Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.bson.types.ObjectId;
import static com.pharmacy.Methods.Methods.auth;


public class Login {
    //Statements
    public static String name;
    public static String position;
    public static ObjectId id;

    //FXML functional elements
    @FXML
    private Button authButton;

    @FXML
    private TextField SingUpLogin;

    @FXML
    private PasswordField SingUpPass;


    //On pushing enter button action
    @FXML
    public void onEnter(ActionEvent event){
        auth(event, SingUpLogin.getText().trim(), SingUpPass.getText().trim());
    }

    @FXML
    void initialize(){
        //Authorisation action
        authButton.setOnAction(event ->auth(event, SingUpLogin.getText().trim(), SingUpPass.getText().trim()));
    }
}