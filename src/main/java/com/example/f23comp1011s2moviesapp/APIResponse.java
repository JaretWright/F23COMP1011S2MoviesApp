package com.example.f23comp1011s2moviesapp;

import com.google.gson.annotations.SerializedName;

public class APIResponse {

    @SerializedName("Search")
    private Movie[] movies;

    private String totalResults;

//    @SerializedName("Response")
//    private String response;

    public Movie[] getMovies() {
        return movies;
    }

    public String getTotalResults() {
        return totalResults;
    }

//    public String getResponse() {
//        return response;
//    }
}
