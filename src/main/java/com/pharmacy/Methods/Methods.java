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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.pharmacy.Database.Database.foundedMedicine;
import static com.pharmacy.Database.Database.medicine;

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
            alert("Error", "Wrong login or password");
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
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Document document : Database.foundedMedicine) {
            Medicine medicine = new Medicine(
                    document.getString("name"),
                    document.getInteger("quant"),
                    document.getInteger("price"),
                    document.getInteger("code"),
                    df.format(document.getDate("Date"))
            );
            meds.add(medicine);
        }
        return meds;
    }

    //Search medicine
    public static Medicine getMed(String parameter) {
        Medicine medicine = new Medicine("", 0, 0, 0, "");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Document document : Database.foundedMedicine) {
            try {
                if (document.getString("name").equals(parameter)) {
                    medicine.setName(document.getString("name"));
                    medicine.setQuant(document.getInteger("quant"));
                    medicine.setPrice(document.getInteger("price"));
                    medicine.setSerialNumber(document.getInteger("code"));
                    medicine.setDate(df.format(document.getDate("Date")));
                } else if(document.getInteger("code").equals(Integer.parseInt(parameter))) {
                    medicine.setName(document.getString("name"));
                    medicine.setQuant(document.getInteger("quant"));
                    medicine.setPrice(document.getInteger("price"));
                    medicine.setSerialNumber(document.getInteger("code"));
                    medicine.setDate(df.format(document.getDate("Date")));
                }
            } catch (NumberFormatException ignored) {}

        }
        if (medicine.getName().equals("")) {
            alert("No medicine found", "Wrong name or serial number");
        }
        return medicine;
    }

    //Getting needed medicine
    public static ObservableList getNeededMeds() {
        ObservableList<Medicine> meds = FXCollections.observableArrayList();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Document document : Database.foundedMedicine) {
            if (document.getInteger("quant") < 10) {
                Medicine medicine = new Medicine(
                        document.getString("name"),
                        document.getInteger("quant"),
                        document.getInteger("price"),
                        document.getInteger("code"),
                        df.format(document.getDate("Date"))
                );
                meds.add(medicine);
            }
        }

        return meds;
    }

    //Getting medicine with discount
    public static ObservableList getMedsWithDiscount() {
        ObservableList<Medicine> meds = FXCollections.observableArrayList();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Document document : Database.foundedMedicine) {
            if (document.getInteger("discount") != null) {
                Medicine medicine = new Medicine(
                        document.getString("name"),
                        document.getInteger("quant"),
                        document.getInteger("price"),
                        document.getInteger("code"),
                        df.format(document.getDate("Date")),
                        document.getInteger("discount")
                );
                meds.add(medicine);
            }
        }
        return meds;
    }

    //Making new order
    public static void makeOrder(String name, int quant, String address) {
        Document order = new Document();
        for (Document document : Database.foundedMedicine) {
            if (document.getString("name").equals(name)) {
                order.append("medicine", name);
                order.append("quantity", quant);
                order.append("address", address);
                if (document.getInteger("discount") != null) {
                    order.append("totalSum", (document.getInteger("price") - ((document.getInteger("price")/100)*document.getInteger("discount")) * quant + 200));
                } else {
                    order.append("totalSum", document.getInteger("price") * quant);
                }
                Database.orders.insertOne(order);
            }
        }
    }
}
