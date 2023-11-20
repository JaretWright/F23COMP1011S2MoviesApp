package com.example.f23comp1011s2moviesapp;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class Experiment {
    public static void main(String[] args) {

//       APIResponse apiResponse = APIUtility.getMoviesFromFile("movies.json");
//       Movie[] movies = APIUtility.getMovieArrayFromFile("moviesArray.json");
//       System.out.println(apiResponse);

        try {
            MovieDetails movieDetails = APIUtility.getMovieDetails("tt0116483");
            System.out.println(movieDetails);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
