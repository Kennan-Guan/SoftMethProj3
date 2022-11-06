package com.example.project3;
/**
 * Driver class to run the GymManager class.
 * This class implements a main method that will run the GymManager class and thus initializes
 * the interactive user interface.
 * @author Kennan Guan, Adwait Ganguly
 */
public class RunProject1 {
    /**
     * This method starts and runs the Gym Manager.
     * @param args is the input from the command line given by the user.
     */
    public static void main(String[] args) {
        new GymManager().run();
    }
}