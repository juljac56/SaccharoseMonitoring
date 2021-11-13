module com.example.saccaei {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires javafx.graphics;
    requires java.sql;

    opens com.example.saccaei to javafx.fxml;
    exports com.example.saccaei;
}