package com.pharmacy.Controllers;

//Imports
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static com.pharmacy.Methods.Methods.getCompletedOrders;

public class Salary {

    //FXML functional elements
    @FXML
    private Button HomeButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private Label orders;

    @FXML
    private Label salary;

    @FXML
    void initialize() {
        //Log out button action
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));

        //Home button action
        HomeButton.setOnAction(event -> Methods.loadStage(Login.position, event));

        //Setting orders and salary
        int courierOrder = getCompletedOrders(Login.id).size();
        int courierSalary = courierOrder * 100;
        orders.setText(courierOrder + " orders");
        salary.setText(Integer.toString(courierSalary));
    }
}
