package com.pharmacy.Methods;

//Imports
import com.mongodb.client.FindIterable;
import com.pharmacy.Classes.Medicine;
import com.pharmacy.Classes.Order;
import com.pharmacy.Database.Database;
import com.pharmacy.Controllers.Login;
import com.pharmacy.Main;
import static com.mongodb.client.model.Filters.eq;
import static com.pharmacy.Database.Database.*;

//Javafx imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//Java util and org imports
import org.bson.Document;
import org.bson.types.ObjectId;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    //Getting medicines with a large amount
    public static ObservableList getLargeMeds() {
        ObservableList<Medicine> meds = FXCollections.observableArrayList();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Document document : Database.foundedMedicine) {
            if (document.getInteger("quant") > 90) {
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
                order.append("status", "ordered");
                if (document.getInteger("discount") != null) {
                    order.append("totalSum", (document.getInteger("price") - ((document.getInteger("price")/100)*document.getInteger("discount")) * quant + 200));
                } else {
                    order.append("totalSum", document.getInteger("price") * quant);
                }
                Database.orders.insertOne(order);
            }
        }
    }

    //Getting orders
    public static ObservableList getOrders() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        for (Document document : foundedOrders) {
            Order order = new Order(
                    document.getObjectId("_id"),
                    document.getString("medicine"),
                    document.getInteger("totalSum"),
                    document.getInteger("quantity"),
                    document.getString("address")
            );
            orders.add(order);
        }
        return orders;
    }

    //Delete order
    public static void deleteOrder(ObjectId id) {
        orders.deleteOne(eq("_id", id));
    }

    //Getting data for diagram
    public static ObservableList getData() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Document document : foundedMedicine) {
            PieChart.Data med = new PieChart.Data(document.getString("name"), document.getInteger("quant"));
            pieChartData.add(med);
        }
        return pieChartData;
    }
}
