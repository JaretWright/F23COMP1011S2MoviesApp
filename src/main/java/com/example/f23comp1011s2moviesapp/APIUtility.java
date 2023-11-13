package com.example.f23comp1011s2moviesapp;

public class APIUtility {

    /**
     * This method will call the OMDB API and save the result in a file called
     * movies.json
     */
    public static void searchMovies(String movieName)
    {
        String uri = "http://www.omdbapi.com/?apikey=4a1010ab&s="+movieName;

        //configure the environment to make a HTTP request (this includes an update to
        //the module-info.java file



    }
}
