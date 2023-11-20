package com.example.f23comp1011s2moviesapp;

import com.google.gson.annotations.SerializedName;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieDetails {
    @SerializedName("Actors")
    private String actors;

    @SerializedName("Director")
    private String director;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Poster")
    private String posterUrl;

    @SerializedName("Rated")
    private String rated;

    @SerializedName("Ratings")
    private ArrayList<Rating> ratings;

   @SerializedName("Runtime")
   private String runtime;

    @SerializedName("Title")
    private String title;

    @SerializedName("Writer")
    private String writer;


    /**
     * This takes the list of Actor/Actress names and returns them as a List of String
     * @return
     */
    private List<String> getActorList()
    {
        return Arrays.asList(actors.split(", "));
    }
}
