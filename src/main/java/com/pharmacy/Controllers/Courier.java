package com.pharmacy.Controllers;

//Imports
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Courier {

    //FXML functional elements
    @FXML
    private Label CourierLabel;

    @FXML
    private ListView<String> CourierListView;

    @FXML
    private Button LogOutButton;


    //Options for list
    String[] option = {"Take order", "Show delivered orders", "Show my salary"};

    String aktuallOption;

    @FXML
    void initialize(){
        //Setting hello text when user login
        CourierLabel.setText( "Hello, " + Login.name);

        //Filling list
        CourierListView.getItems().addAll(option);

        //Log out button action
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));

        //List action
        CourierListView.setOnMouseClicked(event -> {
            aktuallOption = CourierListView.getSelectionModel().getSelectedItem();

            //Switching pages by clicking option
            switch (aktuallOption) {
                case "Take order" -> Methods.loadStage("takeOrder", event);
                case "Show delivered orders" -> Methods.loadStage("deliveredOrders", event);
                case "Show my salary" -> Methods.loadStage("salary", event);
                default -> {}
            }
        });
    }

}
