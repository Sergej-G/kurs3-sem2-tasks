module com.example.task8_dao {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.task8_dao to javafx.fxml;
    exports com.example.task8_dao;
    exports model;
    opens model to javafx.fxml;
}