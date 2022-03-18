package com.pharmacy.Controllers;

import com.pharmacy.Methods.Methods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Worker  {

    @FXML
    private Label workerLabel;

    @FXML
    private ListView<String> WorkerListView;

    @FXML
    private Button LogOutButton;

    String[] option = {"Show available medicines", "Search medicine", "Report", "Make order", "Ordered medicines", "Needed medicines", "Medicines with discount", "Delete order"};

    String aktuallOption;

    @FXML
    void initialize(){
        workerLabel.setText( "Hello, " + Login.name);
        WorkerListView.getItems().addAll(option);
        LogOutButton.setOnAction(event -> Methods.loadStage("Login", event));
        WorkerListView.setOnMouseClicked(event -> {
            aktuallOption = WorkerListView.getSelectionModel().getSelectedItem();
            switch (aktuallOption) {
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