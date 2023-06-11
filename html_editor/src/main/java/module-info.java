module com.example.task_web2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.example.html_editor to javafx.fxml;
    exports com.example.html_editor;
}