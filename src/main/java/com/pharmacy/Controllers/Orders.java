package com.pharmacy.Controllers;

//Imports
import com.pharmacy.Classes.Order;
import com.pharmacy.Methods.Methods;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import static com.pharmacy.Methods.Methods.*;

public class Orders {

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
    private TableColumn<Order, Order> delete;

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
        delete.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        orders.setItems(getOrders());

        //Adding delete button into the table
        delete.setCellFactory(param -> new TableCell<>(){
            private final Button deleteButton = new Button("Delete");
            private final HBox pane = new HBox(deleteButton);

            @Override
            protected void updateItem(Order order, boolean empty) {
                super.updateItem(order, empty);
                if (order == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(pane);
                deleteButton.setOnAction(event -> {
                    deleteOrder(order.getId());
                    orders.setItems(getOrders());
                });
            }
        });
    }


}
