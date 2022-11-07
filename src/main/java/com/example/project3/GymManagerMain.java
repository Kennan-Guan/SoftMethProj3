package com.example.project3;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * Main class that launches the GUI for GymManager so that the user can actively interact with the
 * backend program and database.
 * @author Kennan Guan, Adwait Ganguly
 */
public class GymManagerMain extends Application {

    /**
     * This method initializes the scene that the GUI will run on and the user will interact with.
     * @param stage is the stage that will be passed into the setScene() to initialize the scene for the GUI.
     * @throws IOException if there is no .fxml file to read the XML input from.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Gym Manager Project 3");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method runs the entire program by calling the launch() method to launch the GUI for the user to interact with.
     * @param args is an array of String objects that is input in the command line from the user.
     */
    public static void main(String[] args) {
        launch();
    }
}