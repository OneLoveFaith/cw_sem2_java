package com.pharmacy.Controllers;

//Imports
import com.pharmacy.Classes.Medicine;
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static com.pharmacy.Methods.Methods.getMedsWithDiscount;

public class Discount {

    //FXML functional elements
    @FXML
    private Button HomeButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private TableColumn<Medicine, String> discount;

    @FXML
    private TableView<Medicine> meds;

    @FXML
    private TableColumn<Medicine, Integer> name;

    @FXML
    void initialize() {
        //Log out button action
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));

        //Home button action
        HomeButton.setOnAction(event -> Methods.loadStage(Login.position, event));

        //Filling table
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        meds.setItems(getMedsWithDiscount());
    }

}
