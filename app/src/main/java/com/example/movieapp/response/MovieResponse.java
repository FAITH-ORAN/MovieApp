package com.example.movieapp.response;

import com.example.movieapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// this clas is for single movie req
public class MovieResponse {

    // finding the movie object

    @SerializedName("results")
    @Expose

    private MovieModel movie;
    public MovieModel getMovie(){
        return movie;
    }


    // pour afficher le results
    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
