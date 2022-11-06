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
    private void printDefault() {
        informationText.appendText("\n" + database.print());
    }

    @FXML
    private void printByName() {
        informationText.appendText("\n" + database.printByName());
    }

    @FXML
    private void printByCounty() {
        informationText.appendText("\n" + database.printByCounty());
    }

    @FXML
    private void printByExpiration() {
        informationText.appendText("\n" + database.printByExpirationDate());
    }

    @FXML
    private void printByFee() {
        informationText.appendText("\n" + database.printWithMembershipFee());
    }

    @FXML
    private void loadClasses() {
        try {
            String output = "";
            Scanner sc = new Scanner(new File("src/main/java/classSchedule.txt"));

            while (sc.hasNextLine()) {
                String[] inputs = sc.nextLine().split("\\s+");
                String classType = inputs[0].toUpperCase();
                String instructor = inputs[1].toUpperCase();
                Time time = Time.valueOf(inputs[2].toUpperCase());
                Location gymLocation = Location.valueOf(inputs[3].toUpperCase());

                listOfClasses.addClass(new FitnessClass(time, instructor, classType, gymLocation));
            }
            output += "-Fitness classes loaded-\n";
            for (int i = 0; i < listOfClasses.getSize(); i++) {
                output += listOfClasses.getClasses()[i].toString() + "\n";
            }
            output += "-end of class list.\n";
            informationText.appendText(output);
        } catch (FileNotFoundException e) {
            informationText.appendText("classSchedule.txt file not found.\n");
        }
    }

    @FXML
    private void printClasses() {
        informationText.appendText(listOfClasses.printClasses());
    }

    @FXML
    private void bulkLoad() {
        String output = "";
        try {
            Scanner readMem = new Scanner(new File("src/main/java/memberList.txt"));
            while (readMem.hasNextLine()) {
                Member newMem = new Member(readMem.nextLine());
                database.add(newMem);
            }
            output += "-list of members loaded-\n";
            output += database.print() + "\n";
            output += "-end of list-\n";
            informationText.appendText(output);
        } catch (FileNotFoundException e) {
            informationText.appendText("memberList.txt file not found\n");
        }
    }

    @FXML
    void initialize() {
        assert MembType != null : "fx:id=\"MembType\" was not injected: check your FXML file 'GymManagerView.fxml'.";

        database = new MemberDatabase();
        listOfClasses = new ClassSchedule();

    }
}