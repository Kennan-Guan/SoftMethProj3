package com.example.project3;

import javafx.event.ActionEvent;
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

    private static final int STANDARDEXPIRATION = 4;

    @FXML
    private Button addMembButton;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private RadioButton familyButton;

    @FXML
    private ComboBox<?> membLocation;

    @FXML
    private TextArea outputMembArea;

    @FXML
    private RadioButton premiumButton;

    @FXML
    private Button removeMembButton;

    @FXML
    private RadioButton standardButton;
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;
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
    private void addMember(ActionEvent event) {
        if (standardButton.isSelected()) {
            addStandardMember();
        }
        if (familyButton.isSelected()) {
            addFamilyMember();
        }
        if (premiumButton.isSelected()) {
            addPremiumMember();
        }
    }
    @FXML
    private void addStandardMember() {
        String fName = firstName.getText();
        String lName = lastName.getText();
        Date dayOfBirth = new Date(dateOfBirth.getValue().toString(), 1);
        String city = membLocation.getSelectionModel().getSelectedItem().toString();
        boolean validCity = false;

        for (Location location : Location.values()) {
            if (location.name().equals(city.toUpperCase())) {
                validCity = true;
            }
        }
        if (!validCity) {
            System.out.println(city + ": invalid location!");
        } else if (!dayOfBirth.isValid()) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": invalid calendar date!");
        } else if (!dayOfBirth.isFuture(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": cannot be today or a future date!");
        } else if (!dayOfBirth.isEighteen(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": must be 18 or older to join!");
        } else {
            Member newEntry = new Member(fName, lName, dayOfBirth.toString(), city, STANDARDEXPIRATION);
            if (database.add(newEntry)) {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " added.");
            } else {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " is already in the database.");
            }
        }
    }

    /**
     * Helper method to add a family membership into the database.
     *
     */
    private void addFamilyMember() {
        String fName = firstName.getText();
        String lName = lastName.getText();
        Date dayOfBirth = new Date(dateOfBirth.getValue().toString(), 1);
        String city = membLocation.getSelectionModel().getSelectedItem().toString();
        boolean validCity = false;

        for (Location location : Location.values()) {
            if (location.name().equals(city.toUpperCase())) {
                validCity = true;
            }
        }

        if (!validCity) {
            outputMembArea.appendText(city + ": invalid location!");
        } else if (!dayOfBirth.isValid()) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": invalid calendar date!");
        } else if (!dayOfBirth.isFuture(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": cannot be today or a future date!");
        } else if (!dayOfBirth.isEighteen(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": must be 18 or older to join!");
        } else {
            Member newEntry = new Family(fName + " " + lName + " " + dayOfBirth.toString() + " " + city);
            if (database.add(newEntry)) {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " added.");
            } else {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " is already in the database.");
            }
        }
    }

    /**
     * Helper method to add a premium membership into the database.
     *
     */
    private void addPremiumMember() {
        String fName = firstName.getText();
        String lName = lastName.getText();
        Date dayOfBirth = new Date(dateOfBirth.getValue().toString(), 1);
        String city = membLocation.getSelectionModel().getSelectedItem().toString();

        boolean validCity = false;

        for (Location location : Location.values()) {
            if (location.name().equals(city.toUpperCase())) {
                validCity = true;
            }
        }

        if (!validCity) {
            outputMembArea.appendText(city + ": invalid location!");
        } else if (!dayOfBirth.isValid()) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": invalid calendar date!");
        } else if (!dayOfBirth.isFuture(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": cannot be today or a future date!");
        } else if (!dayOfBirth.isEighteen(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": must be 18 or older to join!");
        } else {
            Member newEntry = new Premium(fName + " " + lName + " " + dayOfBirth.toString() + " " + city);
            if (database.add(newEntry)) {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " added.");
            } else {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " is already in the database.");
            }
        }
    }


    /**
     * Helper method that cancels and removes a member from the member database.
     *
     */
    @FXML
    private void cancelMembership(ActionEvent event) {
        String fName = firstName.getText();
        String lName = lastName.getText();
        Date dayOfBirth = new Date(dateOfBirth.getValue().toString(), 1);

        Member entry = new Member(fName, lName, dayOfBirth.toString());
        if (database.remove(entry)) {
            outputMembArea.appendText(fName + " " + lName + " removed." + "\n");
            outputMembArea.appendText("\n");
        } else {
            outputMembArea.appendText(fName + " " + lName + " is not in the database.");
        }
    }

    @FXML
    void initialize() {
        assert MembType != null : "fx:id=\"MembType\" was not injected: check your FXML file 'GymManagerView.fxml'.";

        database = new MemberDatabase();
        listOfClasses = new ClassSchedule();

    }
}