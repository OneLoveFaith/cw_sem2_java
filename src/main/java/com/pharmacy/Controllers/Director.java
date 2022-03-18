package com.pharmacy.Controllers;

import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Director {

    @FXML
    private ListView<String> DirectorListView;

    @FXML
    private Button LogOutButton;

    @FXML
    private Label DirectorLabel;

    String[] option = {"Add worker", "Show available medicines", "Search medicine", "Report", "Make order", "Ordered medicines", "Needed medicines", "Medicines with discount", "Delete order"};

    String aktuallOption;

    @FXML
    void initialize(){
        DirectorLabel.setText( "Hello, " + Login.name);
        DirectorListView.getItems().addAll(option);
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));
        DirectorListView.setOnMouseClicked(event -> {
            aktuallOption = DirectorListView.getSelectionModel().getSelectedItem();
            switch (aktuallOption) {
                case "Add worker" -> Methods.loadStage("addWorker", event);
                case "Show available medicines" -> Methods.loadStage("medList", event);
                case "Search medicine" -> {}
                case "Report" -> {}
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
