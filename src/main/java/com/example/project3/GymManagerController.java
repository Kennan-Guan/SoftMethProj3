package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GymManagerController {

    MemberDatabase database;
    ClassSchedule listOfClasses;

    @FXML
    private TextArea informationText;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup MembType;

    @FXML
    private void printDefault(){
        informationText.appendText("\n" + database.print());
    }

    @FXML
    private void printByName(){
        informationText.appendText("\n" + database.printByName());
    }

    @FXML
    private void printByCounty(){
        informationText.appendText("\n" + database.printByCounty());
    }

    @FXML
    private void printByExpiration(){
        informationText.appendText("\n" + database.printByExpirationDate());
    }

    @FXML
    private void printByFee(){
        informationText.appendText("\n" + database.printWithMembershipFee());
    }

    @FXML
    void initialize() {
        assert MembType != null : "fx:id=\"MembType\" was not injected: check your FXML file 'GymManagerView.fxml'.";

        database = new MemberDatabase();
        listOfClasses = new ClassSchedule();

    }
}