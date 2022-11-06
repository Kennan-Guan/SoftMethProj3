package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ToggleGroup;

public class GymManagerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup MembType;

    @FXML
    void initialize() {
        assert MembType != null : "fx:id=\"MembType\" was not injected: check your FXML file 'GymManagerView.fxml'.";

    }
}