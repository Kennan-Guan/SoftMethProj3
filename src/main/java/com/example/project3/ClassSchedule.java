package com.example.project3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Defines a schedule of fitness classes.
 * A class schedule is defined by a list of classes on the schedule and a counter to keep track of the classes in the list.
 * Offers functionality to add classes amd grow automatically.
 * Has two getter methods to get the size and the class list.
 * @author Kennan Guan, Adwait Ganguly
 */
public class ClassSchedule {
    private FitnessClass[] classes;
    private int numClasses;
    private static final int ARRAYGROWTH = 4;

    /**
     * Constructor for class schedule.
     * Sets the array size to a default number of four and number of classes to 0.
     */
    public ClassSchedule() {
        classes = new FitnessClass[ARRAYGROWTH];
        numClasses = 0;
    }

    /**
     * Will grow the size of the classes array by an increment of four.
     * Called by the addClass() method.
     */
    private void grow() {
        FitnessClass[] newSchedule = new FitnessClass[numClasses + ARRAYGROWTH];

        for (int i = 0; i < numClasses; i++) {
            newSchedule[i] = classes[i];
        }

        classes = newSchedule;

    }

    /**
     * Adds a specified fitness class into the schedule of classes.
     * @param fitness the fitness class to be added.
     */
    public void addClass(FitnessClass fitness) {
        if (numClasses == classes.length) {
            grow();
        }

        classes[numClasses] = fitness;
        numClasses++;
    }

    /**
     * Getter method for the number of classes in the array.
     * @return the number of classes
     */
    public int getSize() {
        return numClasses;
    }

    /**
     * Getter method for the list of fitness classes.
     * @return the list of fitness classes
     */
    public FitnessClass[] getClasses() {
        return classes;
    }

    /**
     * Prints all the fitness classes available
     * @return a string with a list of fitness classes
     */
    public String printClasses() {
        String output = "";
        if (numClasses == 0) {
            return "Fitness class schedule is empty.\n\n";
        }
        else {
            output += "-Fitness classes-\n";
            for (int i = 0; i < numClasses; i++) {
                output += classes[i] + "\n";
                Member[] attendance = classes[i].getList();
                if (attendance.length != 0) {
                    output += "- Participants -\n";
                }
                for (int j = 0; j < attendance.length; j++) {
                    output += "   ";
                    output += attendance[j] + "\n";
                }

                if (classes[i].getGuests().size() != 0) {
                    output += "- Guests -\n";
                }
                for (int k = 0; k < classes[i].getGuestList().length; k++) {
                    output += "   ";
                    output += classes[i].getGuestList()[k] + "\n";
                }

            }
            output += "-end of class list.\n\n";
        }
        return output;
    }

    /**
     * Loads the schedule of fitness classes from file classSchedule.txt
     * @return a string with the schedule of fitness classes that was loaded
     */
    public String loadFitnessClasses() {
        try {
            String output = "";
            Scanner sc = new Scanner(new File("src/main/java/classSchedule.txt"));

            while (sc.hasNextLine()) {
                String[] inputs = sc.nextLine().split("\\s+");
                String classType = inputs[0].toUpperCase();
                String instructor = inputs[1].toUpperCase();
                Time time = Time.valueOf(inputs[2].toUpperCase());
                Location gymLocation = Location.valueOf(inputs[3].toUpperCase());

                this.addClass(new FitnessClass(time, instructor, classType, gymLocation));
            }
            output += "-Fitness classes loaded-\n";
            for (int i = 0; i < this.getSize(); i++) {
                output += this.getClasses()[i].toString() + "\n";
            }
            output += "-end of class list.\n\n";
            return output;
        } catch (FileNotFoundException e) {
            return "classSchedule.txt file not found.\n";
        }
    }
}
