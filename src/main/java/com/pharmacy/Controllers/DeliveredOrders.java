package com.pharmacy.Controllers;

//Imports
import com.pharmacy.Classes.Order;
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static com.pharmacy.Methods.Methods.getCompletedOrders;

public class DeliveredOrders {
    //FXML functional elements
    @FXML
    private Button HomeButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private TableView<Order> orders;

    @FXML
    private TableColumn<Order, String> address;

    @FXML
    private TableColumn<Order, String> name;

    @FXML
    private TableColumn<Order, Integer> quantity;

    @FXML
    private TableColumn<Order, Integer> sum;

    @FXML
    void initialize() {
        //Log out button action
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));

        //Home button action
        HomeButton.setOnAction(event -> Methods.loadStage(Login.position, event));

        //Filling table
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        name.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quant"));
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        orders.setItems(getCompletedOrders(Login.id));
    }
}
