package com.pharmacy.Controllers;

import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import static com.pharmacy.Methods.Methods.getData;

public class MedicineDiagram {

    @FXML
    private Button HomeButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private PieChart diagram;

    @FXML
    void initialize() {
        //Log out button action
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));

        //Home button action
        HomeButton.setOnAction(event -> Methods.loadStage(Login.position, event));

        diagram.setData(getData());
    }

}
