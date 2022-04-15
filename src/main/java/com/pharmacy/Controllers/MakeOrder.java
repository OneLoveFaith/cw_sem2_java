package com.pharmacy.Controllers;

import com.pharmacy.Classes.Medicine;
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.pharmacy.Methods.Methods.getMed;
import static com.pharmacy.Methods.Methods.makeOrder;

public class MakeOrder {

    @FXML
    private Button HomeButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private TextField address;

    @FXML
    private TextField medicine;

    @FXML
    private TextField quant;

    @FXML
    private Button submitButton;


    @FXML
    void initialize(){
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));
        HomeButton.setOnAction(event -> Methods.loadStage(Login.position, event));
        submitButton.setOnAction(event -> {
            Medicine med = getMed(medicine.getText());
            makeOrder(med.getName(), Integer.parseInt(quant.getText()), address.getText());
        });
    }

}