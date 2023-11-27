package com.example.f23comp1011s2moviesapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
    private Button fetchAllButton;

    @FXML
    private ListView<Movie> listView;

    @FXML
    private Label msgLabel;

    @FXML
    private Label infoLabel;

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
    private VBox searchResultsVBox;

    private ArrayList<LocalDateTime> apiCallTimes;

    private int totalNumOfMovies, page;
    @FXML
    private void initialize()
    {
        apiCallTimes = new ArrayList<>();
        progressBar.setVisible(false);
        selectedVBox.setVisible(false);
        msgLabel.setVisible(false);
        infoLabel.setVisible(false);
        searchResultsVBox.setVisible(false);

        //configure the listview with a listener so that when a movie is selected, it will
        //display the poster art
        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, movieSelected)->{
                    if (movieSelected != null)
                    {
                        selectedVBox.setVisible(true);
                        try {
                            posterImageView.setImage(new Image(movieSelected.getPoster()));
                        }catch (IllegalArgumentException e)
                        {
                            posterImageView.setImage(new Image(Main.class
                                                .getResourceAsStream("images/default_poster.png")));
                        }
                    }
                    else
                    {
                        selectedVBox.setVisible(false);
                    }
                });
    }

    @FXML
    void searchOMBD(ActionEvent event) throws IOException, InterruptedException {
        String movieName = searchTextField.getText();
        clearOldTimeStamps();
        page=1;
        msgLabel.setVisible(false);
        if (!movieName.trim().isEmpty())
        {
            apiCallTimes.add(LocalDateTime.now());
            if (apiCallTimes.size()<20) {
                APIResponse apiResponse = APIUtility.searchMovies(movieName.trim(), page);
                if (apiResponse!=null)
                {
                    totalNumOfMovies = Integer.parseInt(apiResponse.getTotalResults());
                    searchResultsVBox.setVisible(true);
                    listView.getItems().clear();
                    infoLabel.setVisible(true);
                    if (apiResponse.getMovies() != null)
                    {
                        listView.getItems().addAll(apiResponse.getMovies());
                        updateLabels();
                    }
                    else
                    {
                        infoLabel.setText("No movies with that title were found");
                    }
                }
                msgLabel.setText("Movie Database service did not respond");
            }
            else
            {
                infoLabel.setVisible(false);
                msgLabel.setVisible(true);
                msgLabel.setText("Too many call attempts, wait a minute");
            }
        }
        else
        {
            msgLabel.setVisible(true);
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

    /**
     * This method will take the currently selected movie and pass the imdbID into
     * the details view.  It will also change the scene
     */
    @FXML
    private void detailsView(ActionEvent event) throws IOException {
        //get a selected item out of the listview
        Movie movieSelected = listView.getSelectionModel()
                                        .selectedItemProperty().getValue();
        SceneChanger.changeScenes(event, "details-view.fxml",movieSelected.getImdbID());
    }

    @FXML
    void fetchAllMovies() throws IOException, InterruptedException {
        page++;
        APIResponse apiResponse = APIUtility.searchMovies(searchTextField.getText().trim(), page);
        listView.getItems().addAll(apiResponse.getMovies());

        //check if all the movies are loaded, if not call the method again
        //this is a recursive call
        if (listView.getItems().size()<totalNumOfMovies)
            fetchAllMovies();
        updateLabels();
    }

    private void updateLabels()
    {
        infoLabel.setText("Showing " + listView.getItems().size() + " of " + totalNumOfMovies);
        if (listView.getItems().size()<totalNumOfMovies)
            fetchAllButton.setVisible(true);
        else
            fetchAllButton.setVisible(false);
    }
}
