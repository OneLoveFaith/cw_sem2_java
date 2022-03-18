module com.example.pharmacy {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;


    opens com.pharmacy to javafx.fxml;
    opens com.pharmacy.Classes;
    exports com.pharmacy;
    exports com.pharmacy.Controllers;
    opens com.pharmacy.Controllers to javafx.fxml;
}