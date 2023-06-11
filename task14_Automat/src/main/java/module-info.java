module com.example.task14_automat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task14_automat to javafx.fxml;
    exports com.example.task14_automat;
}