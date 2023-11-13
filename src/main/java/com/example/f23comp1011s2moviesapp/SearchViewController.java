package com.example.f23comp1011s2moviesapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SearchViewController {

    @FXML
    private ListView<Movie> listView;

    @FXML
    private Label msgLabel;

    @FXML
    private ImageView posterImageView;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private HBox resultsBox;

    @FXML
    private TextField searchTextField;

    @FXML
    private VBox selectedVBox;

    @FXML
    private void initialize()
    {
        progressBar.setVisible(false);
    }

    @FXML
    void searchOMBD(ActionEvent event) throws IOException, InterruptedException {
        String movieName = searchTextField.getText();
        if (!movieName.trim().isEmpty())
        {
            APIResponse apiResponse = APIUtility.searchMovies(movieName.trim());
            listView.getItems().clear();
            listView.getItems().addAll(apiResponse.getMovies());
        }
    }
}
