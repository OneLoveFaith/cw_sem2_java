package com.pharmacy.Controllers;

import com.pharmacy.Classes.Medicine;
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import static com.pharmacy.Methods.Methods.getMed;

public class MedSearch {

    @FXML
    private Button HomeButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private Text name;

    @FXML
    private Text price;

    @FXML
    private Text quant;

    @FXML
    private TextField search;

    @FXML
    private Text serialNumber;

    @FXML
    private Text date;

    @FXML
    private Button searchButton;

    @FXML
    void initialize() {
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));
        HomeButton.setOnAction(event -> Methods.loadStage(Login.position, event));
        searchButton.setOnAction(event -> {
            Medicine medicine = getMed(search.getText().trim());
            name.setText(medicine.getName());
            price.setText(String.valueOf(medicine.getPrice()));
            quant.setText(String.valueOf(medicine.getQuant()));
            serialNumber.setText(String.valueOf(medicine.getSerialNumber()));
            date.setText(medicine.getDate());
        });
    }

}