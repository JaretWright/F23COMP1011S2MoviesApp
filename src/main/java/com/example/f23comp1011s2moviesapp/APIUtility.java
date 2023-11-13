package com.example.f23comp1011s2moviesapp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.nio.file.Path;

public class APIUtility {

    /**
     * This method will call the OMDB API and save the result in a file called
     * movies.json
     */
    public static void searchMovies(String movieName) throws IOException, InterruptedException {
        String uri = "http://www.omdbapi.com/?apikey=4a1010ab&s="+movieName;

        //configure the environment to make a HTTP request (this includes an update to
        //the module-info.java file
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        //this will save the json response to a file call movies.json
        HttpResponse<Path> httpResponse = client.send(httpRequest,
                                        HttpResponse.BodyHandlers.ofFile(Paths.get("movies.json")));
    }
}
