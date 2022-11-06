module com.example.softmethprojfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project3 to javafx.fxml;
    exports com.example.project3;
}