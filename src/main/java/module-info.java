module com.example.softmethprojfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.softmethprojfx to javafx.fxml;
    exports com.example.softmethprojfx;
}