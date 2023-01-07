module com.example.cokolwiek {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cokolwiek to javafx.fxml;
    exports com.example.cokolwiek;
}