package com.example.f23comp1011s2moviesapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class APIResponse {

    @SerializedName("Search")
    private ArrayList<Movie> movies;

    private String totalResults;

    @SerializedName("Response")
    private String response;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }
}
