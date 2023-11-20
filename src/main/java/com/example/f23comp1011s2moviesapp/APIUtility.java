package com.example.f23comp1011s2moviesapp;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
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
    public static APIResponse searchMovies(String movieName) throws IOException, InterruptedException {
        //use the replaceAll method to replace a space with %20
        movieName = movieName.replaceAll(" ","%20");

        String uri = "http://www.omdbapi.com/?apikey=4a1010ab&s="+movieName;

        //configure the environment to make a HTTP request (this includes an update to
        //the module-info.java file
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        //this will save the json response to a file call movies.json
//        HttpResponse<Path> httpResponse = client.send(httpRequest,
//                                        HttpResponse.BodyHandlers.ofFile(Paths.get("movies.json")));

        //this will save the json response to a HttpResponse object
        HttpResponse<String> response = client.send(httpRequest,
                                                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        //Update the pom.xml file for GSON and update module-info.java to work with GSON
        Gson gson = new Gson();
        return gson.fromJson(response.body(), APIResponse.class);

    }

    /**
     * This method will read from "movies.json" and create an APIResponse object
     */
    public static APIResponse getMoviesFromFile(String fileName)
    {
        //Create a GSON object.  GSON is the Google library that can read and write
        //JSON
        //in order to use this library, we need to update the pom.xml and module-info.java
        //files.  Don't forget to reload the Maven dependencies
        Gson gson = new Gson();

        APIResponse apiResponse = null;

        //open the file and pass it into the the Gson object to covert JSON objects
        //to Java objects
        try(
                FileReader fileReader = new FileReader(fileName);
                JsonReader jsonReader = new JsonReader(fileReader);
        )
        {
            apiResponse = gson.fromJson(jsonReader, APIResponse.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return apiResponse;
    }

    public static Movie[] getMovieArrayFromFile(String fileName)
    {
        //Create a GSON object.  GSON is the Google library that can read and write
        //JSON
        //in order to use this library, we need to update the pom.xml and module-info.java
        //files.  Don't forget to reload the Maven dependencies
        Gson gson = new Gson();

        APIResponse apiResponse = null;

        //open the file and pass it into the the Gson object to covert JSON objects
        //to Java objects
        try(
                FileReader fileReader = new FileReader(fileName);
                JsonReader jsonReader = new JsonReader(fileReader);
        )
        {
            return gson.fromJson(jsonReader, Movie[].class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
