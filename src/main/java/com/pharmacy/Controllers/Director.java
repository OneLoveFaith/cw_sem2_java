package com.pharmacy.Controllers;

//Imports
import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Director {

    //FXML functional elements
    @FXML
    private ListView<String> DirectorListView;

    @FXML
    private Button LogOutButton;

    @FXML
    private Label DirectorLabel;

    //Options for list
    String[] option = {"Add worker", "Show available medicines", "Search medicine", "Report", "Make order", "Ordered medicines", "Needed medicines", "Medicines with discount", "Delete order"};

    String aktuallOption;

    @FXML
    void initialize(){
        //Setting hello text when user login
        DirectorLabel.setText( "Hello, " + Login.name);

        //Filling list
        DirectorListView.getItems().addAll(option);

        //Log out button action
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));

        //List action
        DirectorListView.setOnMouseClicked(event -> {
            aktuallOption = DirectorListView.getSelectionModel().getSelectedItem();

            //Switching pages by clicking option
            switch (aktuallOption) {
                case "Add worker" -> Methods.loadStage("addWorker", event);
                case "Show available medicines" -> Methods.loadStage("medList", event);
                case "Make order" -> {}
                case "Ordered medicines" -> {}
                case "Needed medicines" -> {}
                case "Medicines with discount" -> {}
                case "Delete order" -> {}
                default -> {}
            }
        });
    }

}
