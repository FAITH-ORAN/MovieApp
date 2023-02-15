package com.example.movieapp.response;

import com.example.movieapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// Movies list
public class MovieSearchResponse {


    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("results")
    @Expose
    private List<MovieModel> movies;

  public int getPages(){
      return page;
  }

   public List<MovieModel> getMovies(){
      return movies;
   }


    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "page=" + page +
                ", movies=" + movies +
                '}';
    }
}
