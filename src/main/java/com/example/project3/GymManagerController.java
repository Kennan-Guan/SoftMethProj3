package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GymManagerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}