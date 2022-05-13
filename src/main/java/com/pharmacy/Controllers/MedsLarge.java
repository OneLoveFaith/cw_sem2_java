package com.pharmacy.Controllers;

//Imports
import com.pharmacy.Classes.Medicine;
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static com.pharmacy.Methods.Methods.getLargeMeds;

public class MedsLarge {

    //FXML functional elements
    @FXML
    private Button HomeButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private TableView<Medicine> meds;

    @FXML
    private TableColumn<Medicine, String> name;

    @FXML
    private TableColumn<Medicine, Integer> quant;


    @FXML
    void initialize() {
        //Log out button action
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));

        //Home button action
        HomeButton.setOnAction(event -> Methods.loadStage(Login.position, event));

        //Filling table
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quant.setCellValueFactory(new PropertyValueFactory<>("quant"));
        meds.setItems(getLargeMeds());
    }

}
