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
    private static int ARRAYGROWTH = 4;

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
     * This loads the fitness classes from a text file.
     */
    private void loadClasses() {
        try {
            Scanner sc = new Scanner(new File("src/classSchedule.txt"));

            while (sc.hasNextLine()) {
                String[] inputs = sc.nextLine().split("\\s+");
                String classType = inputs[0].toUpperCase();
                String instructor = inputs[1].toUpperCase();
                Time time = Time.valueOf(inputs[2].toUpperCase());
                Location gymLocation = Location.valueOf(inputs[3].toUpperCase());

                this.addClass(new FitnessClass(time, instructor, classType, gymLocation));
            }
            System.out.println("-Fitness classes loaded-");
            for (int i = 0; i < this.getSize(); i++) {
                System.out.println(this.getClasses()[i].toString());
            }
            System.out.println("-end of class list.");
        } catch (FileNotFoundException e) {
            System.out.println("classSchedule.txt file not found.");
        }
    }

    /**
     * This method prints the class schedules.
     */
    public void printClasses() {
        if (numClasses == 0) {
            System.out.println("Fitness class schedule is empty.");
            System.out.println();
        }
        else {
            System.out.println("-Fitness classes-");
            for (int i = 0; i < numClasses; i++) {
                System.out.println(classes[i]);
                Member[] attendance = classes[i].getList();
                if (attendance.length != 0) {
                    System.out.println("- Participants -");
                }
                for (int j = 0; j < attendance.length; j++) {
                    System.out.print("   ");
                    System.out.println(attendance[j]);
                }

                if (classes[i].getGuests().size() != 0) {
                    System.out.println("- Guests -");
                }
                for (int k = 0; k < classes[i].getGuestList().length; k++) {
                    System.out.print("   ");
                    System.out.println(classes[i].getGuestList()[k]);
                }

            }
            System.out.println("-end of class list.");
            System.out.println();
        }
    }


}
