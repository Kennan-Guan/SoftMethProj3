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
    private DatePicker dateOfBirthFitness;

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
    private TextField firstNameFitness;

    @FXML
    private TextField lastNameFitness;

    @FXML
    private TextArea informationText;

    @FXML
    private TextArea fitnessText;

    @FXML
    private ComboBox classType;

    @FXML
    private ComboBox gymLocation;

    @FXML
    private ComboBox fitnessInstructor;

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
        informationText.appendText(listOfClasses.loadFitnessClasses());
    }

    @FXML
    private void printClasses() {
        informationText.appendText(listOfClasses.printClasses());
    }

    @FXML
    private void loadMem() {
        informationText.appendText(database.bulkLoad());
    }

    @FXML
    private void addMember(ActionEvent event) {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || dateOfBirth.getValue() == null || membLocation.getSelectionModel().isEmpty()) {
            outputMembArea.appendText("Please fill out all information!\n");
            return;
        }
        if (standardButton.isSelected()) {
            addStandardMember();
        }
        if (familyButton.isSelected()) {
            addFamilyMember();
        }
        if (premiumButton.isSelected()) {
            addPremiumMember();
        }
        else {
            outputMembArea.appendText("Please select a membership type.\n");
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
            System.out.println(city + ": invalid location!\n");
        } else if (!dayOfBirth.isValid()) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": invalid calendar date!\n");
        } else if (!dayOfBirth.isFuture(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": cannot be today or a future date!\n");
        } else if (!dayOfBirth.isEighteen(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": must be 18 or older to join!\n");
        } else {
            Member newEntry = new Member(fName, lName, dayOfBirth.toString(), city, STANDARDEXPIRATION);
            if (database.add(newEntry)) {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " added.\n");
            } else {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " is already in the database.\n");
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
            outputMembArea.appendText(city + ": invalid location!\n");
        } else if (!dayOfBirth.isValid()) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": invalid calendar date!\n");
        } else if (!dayOfBirth.isFuture(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": cannot be today or a future date!\n");
        } else if (!dayOfBirth.isEighteen(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": must be 18 or older to join!\n");
        } else {
            Member newEntry = new Family(fName + " " + lName + " " + dayOfBirth.toString() + " " + city + "\n");
            if (database.add(newEntry)) {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " added.\n");
            } else {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " is already in the database.\n");
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
            outputMembArea.appendText(city + ": invalid location!\n");
        } else if (!dayOfBirth.isValid()) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": invalid calendar date!\n");
        } else if (!dayOfBirth.isFuture(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": cannot be today or a future date!\n");
        } else if (!dayOfBirth.isEighteen(dayOfBirth)) {
            outputMembArea.appendText("DOB " + dayOfBirth + ": must be 18 or older to join!\n");
        } else {
            Member newEntry = new Premium(fName + " " + lName + " " + dayOfBirth.toString() + " " + city + "\n");
            if (database.add(newEntry)) {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " added.\n");
            } else {
                outputMembArea.appendText(newEntry.getFname() + " " + newEntry.getLname() + " is already in the database.\n");
            }
        }
    }


    /**
     * Helper method that cancels and removes a member from the member database.
     *
     */
    @FXML
    private void cancelMembership(ActionEvent event) {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || dateOfBirth.getValue() == null ) {
            outputMembArea.appendText("Please fill out first name, last name, and date of birth!\n");
            return;
        }
        String fName = firstName.getText();
        String lName = lastName.getText();
        Date dayOfBirth = new Date(dateOfBirth.getValue().toString(), 1);

        Member entry = new Member(fName, lName, dayOfBirth.toString());
        if (database.remove(entry)) {
            outputMembArea.appendText(fName + " " + lName + " removed." + "\n");
            outputMembArea.appendText("\n");
        } else {
            outputMembArea.appendText(fName + " " + lName + " is not in the database.\n");
        }
    }

    /**
     * Helper method that adds a new member into a specified fitness class.
     */
    @FXML
    private void checkIn() {
        String fName = firstNameFitness.getText();
        String lName = lastNameFitness.getText();
        Date DOB = new Date(dateOfBirthFitness.getValue().toString(), 1);
        String dateOfBirth = DOB.toString();
        String className = classType.getSelectionModel().getSelectedItem().toString();
        String gym = gymLocation.getSelectionModel().getSelectedItem().toString();
        String instructor = fitnessInstructor.getSelectionModel().getSelectedItem().toString();
        Member entry = new Member(fName, lName, dateOfBirth);
        Member storedEntry = database.isMemberInArray(entry);
        Date today = new Date();

        if(validClass(className) && validGym(gym) && validInstructor(instructor)) {
            if (!DOB.isValid()) {
                fitnessText.appendText("DOB " + DOB + ": invalid calendar date!\n");
            } else if (storedEntry == null) {
                fitnessText.appendText(fName + " " + lName + " " + dateOfBirth + " is not in the database.\n");
            } else if (today.compareTo(storedEntry.getExpire()) >= 0) {
                fitnessText.appendText(fName + " " + lName + " " + dateOfBirth + " membership expired.\n");
            } else {
                FitnessClass checkInClass = findClass(listOfClasses.getClasses(), gym, instructor, className);
                if (checkInClass == null) {
                    fitnessText.appendText(className + " by " + instructor + " does not exist at " + gym + "\n");
                } else if (findConflict(checkInClass.getTime(), storedEntry, instructor)) {
                    fitnessText.appendText("Time conflict - " + checkInClass + ", " + checkInClass.getLocation().getStringZip() + ", " + checkInClass.getLocation().getCounty() + "\n");
                } else if (Location.valueOf(gym.toUpperCase()) != storedEntry.getLocation() && !(storedEntry instanceof Family)) {
                    fitnessText.appendText(storedEntry.getFname() + " " + storedEntry.getLname() + " checking in " + checkInClass.getLocation() + " - standard membership location restriction.\n");
                } else if (checkInClass.addMember(storedEntry)) {
                    fitnessText.appendText(storedEntry.getFname() + " " + storedEntry.getLname() + " checked in " + checkInClass + "\n");
                    fitnessText.appendText("- Participants -\n");
                    for (int i = 0; i < checkInClass.getAttendance().size(); i++) {
                        fitnessText.appendText("   ");
                        fitnessText.appendText(checkInClass.getAttendance().get(i).toString() + "\n");
                    }
                    fitnessText.appendText("\n");
                } else {
                    fitnessText.appendText(storedEntry.getFname() + " " + storedEntry.getLname() + " already checked in.\n");
                }
            }
        }
    }

    /**
     * Helper method to find if the given member has a class at a given time.
     *
     * @param time       the time object of the fitness class
     * @param member     the member object of the member
     * @param instructor the instructor of the class
     * @return true if a conflict exists, false otherwise
     */
    private boolean findConflict(Time time, Member member, String instructor) {
        for (int i = 0; i < listOfClasses.getSize(); i++) {
            FitnessClass fitness = listOfClasses.getClasses()[i];
            if (fitness.getTime() == time && fitness.getAttendance().contains(member) && !fitness.getInstructor().equalsIgnoreCase(instructor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to find the correct fitness class based on location, instructor name, and class name.
     * Will return null if a class is not found or the inputted information is invalid and print an error statement.
     *
     * @param fitnessClasses an array of the fitness classes
     * @param location       the location of the fitness class
     * @param instructor     the name of the instructor of the fitness class
     * @param session        the class name
     * @return the desired fitness class, otherwise null
     */
    private FitnessClass findClass(FitnessClass[] fitnessClasses, String location, String instructor, String session) {
        for (int i = 0; i < fitnessClasses.length - 1; i++) {
            String fitnessName = fitnessClasses[i].getName();
            String fitnessInstructor = fitnessClasses[i].getInstructor();
            Location fitnessLocation = fitnessClasses[i].getLocation();
            if (fitnessName.equalsIgnoreCase(session) && fitnessLocation == Location.valueOf(location.toUpperCase()) && fitnessInstructor.equalsIgnoreCase(instructor)) {
                return fitnessClasses[i];
            }
        }
        return null;
    }

    /**
     * Helper method to determine if the given gym location is valid.
     * Will print an error statement if location is invalid.
     *
     * @param gymLocation the gym location.
     * @return true if the gym is valid, false otherwise.
     */
    private boolean validGym(String gymLocation) {
        for (Location gym : Location.values()) {
            if (gym.name().equalsIgnoreCase(gymLocation)) {
                return true;
            }
        }
        fitnessText.appendText(gymLocation + " - invald location.\n");
        return false;
    }

    /**
     * Helper method to determine if the given instructor name is valid.
     *
     * @param name the name of the instructor.
     * @return true if the instructor is valid, false otherwise.
     */
    private boolean validInstructor(String name) {
        for (int i = 0; i < listOfClasses.getSize(); i++) {
            if (name.equalsIgnoreCase(listOfClasses.getClasses()[i].getInstructor())) {
                return true;
            }
        }
        fitnessText.appendText(name + " - instructor does not exist.\n");
        return false;
    }

    /**
     * Helper method to determine if the given class name is valid.
     *
     * @param name the name of the class
     * @return true if the class is valid, false otherwise.
     */
    private boolean validClass(String name) {
        for (int i = 0; i < listOfClasses.getSize(); i++) {
            if (name.equalsIgnoreCase(listOfClasses.getClasses()[i].getName())) {
                return true;
            }
        }
        fitnessText.appendText(name + " - class does not exist.\n");
        return false;
    }

    @FXML
    private void checkOut() {
        String fName = firstNameFitness.getText();
        String lName = lastNameFitness.getText();
        Date DOB = new Date(dateOfBirthFitness.getValue().toString(), 1);
        String dateOfBirth = DOB.toString();
        String className = classType.getSelectionModel().getSelectedItem().toString();
        String gym = gymLocation.getSelectionModel().getSelectedItem().toString();
        String instructor = fitnessInstructor.getSelectionModel().getSelectedItem().toString();
        Member storedEntry = database.isMemberInArray(new Member(fName, lName, dateOfBirth));

        if (validClass(className) && validGym(gym) && validInstructor(instructor)) {
            if (!DOB.isValid()) {
                fitnessText.appendText("DOB " + DOB + ": invalid calendar date!\n");
            } else if (storedEntry == null) {
                fitnessText.appendText(fName + " " + lName + " " + dateOfBirth + " is not in the database.\n");
            } else {
                FitnessClass checkInClass = findClass(listOfClasses.getClasses(), gym, instructor, className);
                if (checkInClass == null) {
                    fitnessText.appendText(className + " by " + instructor + " does not exist at " + gym + "\n");
                } else if (checkInClass.removeMember(storedEntry)) {
                    fitnessText.appendText(storedEntry.getFname() + " " + storedEntry.getLname() + " done with the class.\n");
                } else {
                    fitnessText.appendText(storedEntry.getFname() + " " + storedEntry.getLname() + " did not check in.\n");
                }
            }
        }
    }

    @FXML
    private void guestCheckIn() {
        String fName = firstNameFitness.getText();
        String lName = lastNameFitness.getText();
        Date DOB = new Date(dateOfBirthFitness.getValue().toString(), 1);
        String dateOfBirth = DOB.toString();
        String className = classType.getSelectionModel().getSelectedItem().toString();
        String gym = gymLocation.getSelectionModel().getSelectedItem().toString();
        String instructor = fitnessInstructor.getSelectionModel().getSelectedItem().toString();
        Member entry = new Member(fName, lName, dateOfBirth);
        Member storedEntry = database.isMemberInArray(entry);
        Date today = new Date();
        FitnessClass checkInClass = findClass(listOfClasses.getClasses(), gym, instructor, className);

        if (validClass(className) && validGym(gym) && validInstructor(instructor)) {
            if (!DOB.isValid()) {
                fitnessText.appendText("DOB " + DOB + ": invalid calendar date!\n");
            } else if (storedEntry == null) {
                fitnessText.appendText(fName + " " + lName + " " + dateOfBirth + " is not in the database.\n");
            } else if (today.compareTo(storedEntry.getExpire()) >= 0) {
                fitnessText.appendText(fName + " " + lName + " " + dateOfBirth + " membership expired.\n");
            } else if (!(storedEntry instanceof Family)) {
                fitnessText.appendText("Standard membership - guest check-in is not allowed.\n");
            } else if (storedEntry.getLocation() != Location.valueOf(gym.toUpperCase())) {
                System.out.println(fName + " " + lName + " Guest checking in " + Location.valueOf(gym.toUpperCase()) + " - guest location restriction.\n");
            } else if (!(storedEntry instanceof Premium)) {
                checkInFamily(storedEntry, checkInClass);
            } else {
                checkInPremium(storedEntry, checkInClass);
            }
        }
    }

    /**
     * Helper method to check in a guest with a family guest pass.
     * Will also print out the participants and guests in the specified class
     *
     * @param member       the owner of the membership
     * @param checkInClass the desired class to check into
     */
    private void checkInFamily(Member member, FitnessClass checkInClass) {
        if (((Family) member).getGuestPasses() <= 0) {
            fitnessText.appendText(member.getFname() + " " + member.getLname() + " ran out of guest pass.\n");
        } else {
            ((Family) member).useGuestPass();
            checkInClass.addGuest(member);
            fitnessText.appendText(member.getFname() + " " + member.getLname() + " (guest) checked in " + checkInClass + "\n");
            if (checkInClass.getAttendance().size() != 0) {
                fitnessText.appendText("- Participants -\n");
                for (int i = 0; i < checkInClass.getAttendance().size(); i++) {
                    fitnessText.appendText("   ");
                    fitnessText.appendText(checkInClass.getAttendance().get(i).toString() + "\n");
                }
            }
            fitnessText.appendText("- Guests -\n");
            for (int i = 0; i < checkInClass.getGuests().size(); i++) {
                fitnessText.appendText("   ");
                fitnessText.appendText(checkInClass.getGuests().get(i).toString() + "\n");
            }
            fitnessText.appendText("\n");
        }
    }

    /**
     * Helper method to check in a guest with a premium guest pass.
     * This method will also print out the participants and guests in the specified class.
     *
     * @param member       the owner of the membership
     * @param checkInClass the desired class to check into
     */
    private void checkInPremium(Member member, FitnessClass checkInClass) {
        if (((Premium) member).getGuestPasses() <= 0) {
            fitnessText.appendText(member.getFname() + " " + member.getLname() + " ran out of guest pass.\n");
        } else {
            ((Premium) member).useGuestPass();
            checkInClass.addGuest(member);
            fitnessText.appendText(member.getFname() + " " + member.getLname() + " (guest) checked in " + checkInClass + "\n");
            if (checkInClass.getAttendance().size() != 0) {
                fitnessText.appendText("- Participants -\n");
                for (int i = 0; i < checkInClass.getAttendance().size(); i++) {
                    fitnessText.appendText("   ");
                    fitnessText.appendText(checkInClass.getAttendance().get(i).toString() + "\n");
                }
            }
            fitnessText.appendText("- Guests -\n");
            for (int i = 0; i < checkInClass.getGuests().size(); i++) {
                fitnessText.appendText("   ");
                fitnessText.appendText(checkInClass.getGuests().get(i).toString() + "\n");
            }
            fitnessText.appendText("\n");
        }
    }

    @FXML
    private void guestCheckOut() {
        String fName = firstNameFitness.getText();
        String lName = lastNameFitness.getText();
        Date DOB = new Date(dateOfBirthFitness.getValue().toString(), 1);
        String dateOfBirth = DOB.toString();
        String className = classType.getSelectionModel().getSelectedItem().toString();
        String gym = gymLocation.getSelectionModel().getSelectedItem().toString();
        String instructor = fitnessInstructor.getSelectionModel().getSelectedItem().toString();
        Member storedEntry = database.isMemberInArray(new Member(fName, lName, dateOfBirth));

        if (!DOB.isValid()) {
            fitnessText.appendText(DOB + ": invalid calendar date!\n");
        } else if (storedEntry == null) {
            fitnessText.appendText(fName + " " + lName + " " + dateOfBirth + " is not in the database.\n");
        } else {
            FitnessClass checkInClass = findClass(listOfClasses.getClasses(), gym, instructor, className);
            if (checkInClass != null) {
                checkInClass.removeGuest(storedEntry);
            }
            fitnessText.appendText(storedEntry.getFname() + " " + storedEntry.getLname() + " Guest done with the class\n");
            if (storedEntry instanceof Family && !(storedEntry instanceof Premium)) {
                ((Family) storedEntry).returnGuestPasses();
            } else if (storedEntry instanceof Premium) {
                ((Premium) storedEntry).returnGuestPasses();
            }
        }
    }

    @FXML
    void initialize() {
        assert MembType != null : "fx:id=\"MembType\" was not injected: check your FXML file 'GymManagerView.fxml'.";

        database = new MemberDatabase();
        listOfClasses = new ClassSchedule();

    }
}