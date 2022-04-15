package com.pharmacy.Controllers;

import com.pharmacy.Classes.Medicine;
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import static com.pharmacy.Methods.Methods.getMeds;

public class MedList {

    @FXML
    private Button LogOutButton;

    @FXML
    private Button HomeButton;

    @FXML
    private TableView<Medicine> meds;

    @FXML
    private TableColumn<Medicine, String> name;

    @FXML
    private TableColumn<Medicine, Integer> price;

    @FXML
    private TableColumn<Medicine, Integer> quant;

    @FXML
    private TableColumn<Medicine, Integer> serialNumber;

    @FXML
    private TableColumn<Medicine, Date> date;

    @FXML
    void initialize() {
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));
        HomeButton.setOnAction(event -> Methods.loadStage(Login.position, event));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quant.setCellValueFactory(new PropertyValueFactory<>("quant"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        serialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        meds.setItems(getMeds());
    }

}


