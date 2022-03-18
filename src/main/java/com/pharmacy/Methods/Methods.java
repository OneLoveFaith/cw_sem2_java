package com.pharmacy.Methods;


//Imports
import com.pharmacy.Classes.Medicine;
import com.pharmacy.Database.Database;
import com.pharmacy.Controllers.Login;
import com.pharmacy.Main;

//Javafx imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//Java util and org imports
import org.bson.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Methods {

    //Changing scene methods
    public static void loadStage(String fxml, MouseEvent event) {
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


    //Authorisation method
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

    //Registration method
    public static void registration(String name, String login, String password, String position) {
        Document document = new Document();
        document.append("name", name);
        document.append("login", login);
        document.append("password", password);
        document.append("position", position);
        Database.users.insertOne(document);
        alert("Success", "New user was added");
    }

    //Alert method
    public static void alert(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.print("");
            }
        });
    }


    //Getting all medicine method
    public static ObservableList getMeds() {
        ObservableList<Medicine> meds = FXCollections.observableArrayList();
        for (Document document : Database.foundedMedicine) {
            Medicine medicine = new Medicine(
                    document.getString("name"),
                    Integer.parseInt(document.getString("quant")),
                    Integer.parseInt(document.getString("price")));
            meds.add(medicine);
        }
        return meds;
    }
}
