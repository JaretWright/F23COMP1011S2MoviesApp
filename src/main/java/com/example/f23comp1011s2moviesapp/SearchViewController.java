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
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private ArrayList<LocalDateTime> apiCallTimes;

    @FXML
    private void initialize()
    {
        apiCallTimes = new ArrayList<>();
        progressBar.setVisible(false);
    }

    @FXML
    void searchOMBD(ActionEvent event) throws IOException, InterruptedException {
        String movieName = searchTextField.getText();
        clearOldTimeStamps();
        msgLabel.setText("");
        if (!movieName.trim().isEmpty())
        {
            apiCallTimes.add(LocalDateTime.now());
            if (apiCallTimes.size()<5) {
                APIResponse apiResponse = APIUtility.searchMovies(movieName.trim());
                listView.getItems().clear();
                listView.getItems().addAll(apiResponse.getMovies());
            }
            else
            {
                msgLabel.setText("Too many call attempts, wait a minute");
            }
        }
        else
        {
            msgLabel.setText("Enter a movie title in the search field");
        }
    }

    //Duration.between(timeStamp.toLocalTime(), LocalDateTime.now()).toSeconds()>60)
    private void clearOldTimeStamps()
    {
        List<LocalDateTime> validTimes = (apiCallTimes.stream()
                .filter(timeStamp -> Duration.between(timeStamp,LocalDateTime.now()).toSeconds()<60)
                .collect(Collectors.toList()));

        apiCallTimes.clear();
        apiCallTimes.addAll(validTimes);
    }
}
