package com.example.f23comp1011s2moviesapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    /**
     * This method will allow us to change scenes and pass in an imdb ID
     */
    public static void changeScenes(ActionEvent event, String fxmlFileName, String imdbID) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));

        //get the stage object from the ActionEvent
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load());

        //get the controller class and call the loadMovie method
        MovieDetails controller = fxmlLoader.getController();
        controller.loadMovie(imdbID);

        stage.setScene(scene);
        stage.show();
    }

    public static void changeScenes(ActionEvent event, String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));

        //get the stage object from the ActionEvent
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
