package com.example.f23comp1011s2moviesapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class DetailsViewController implements LoadMovie {

    @FXML
    private ListView<String> actorsListView;

    @FXML
    private Label directorLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label ratedLabel;

    @FXML
    private ListView<Rating> ratingsListView;

    @FXML
    private Label runTimeLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label writerLabel;

    @FXML
    void returnToSearch(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "search-view.fxml");
    }

    /**
     * This method will receive the imdbID and send it to the API, collect movie details
     * and populate the scene
     */
    public void loadMovie(String imdbID)  {
        try {
            MovieDetails movieDetails = APIUtility.getMovieDetails(imdbID);
            titleLabel.setText(movieDetails.getTitle());
            actorsListView.getItems().addAll(movieDetails.getActorList());
            directorLabel.setText(movieDetails.getDirector());
            genreLabel.setText(movieDetails.getGenre());
            try {
                imageView.setImage(new Image(movieDetails.getPosterUrl()));
            }catch (IllegalArgumentException e)
            {
                imageView.setImage(new Image(Main.class.getResourceAsStream("images/default_poster.png")));
            }
            ratedLabel.setText(movieDetails.getRated());
            ratingsListView.getItems().addAll(movieDetails.getRatings());
            runTimeLabel.setText(movieDetails.getRuntime());
            writerLabel.setText(movieDetails.getWriter());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}