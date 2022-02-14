package com.pharmacy.Methods;

import com.mongodb.client.model.Filters;
import com.pharmacy.Database.Database;
import com.pharmacy.Controllers.Login;
import com.pharmacy.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Methods {
    public static void loadStage(String fxml, MouseEvent event) {
        try {
            System.out.println(fxml);
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml + ".fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadStage(String fxml, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml + ".fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void auth(ActionEvent event, String login , String password) {
        Document founded = Database.users.find(new Document("login", login)).first();
        if (founded != null && password.equals(founded.getString("password"))) {
            String userPosition = founded.getString("position");
            Login.name = founded.getString("name");
            Login.position = userPosition;
            Login.id = founded.getObjectId("_id");
            loadStage(userPosition, event);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong login or password");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.print("");
                }
            });
        }
    }
}
