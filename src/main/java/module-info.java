module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jsoup;
    requires com.fasterxml.jackson.databind;


    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
}