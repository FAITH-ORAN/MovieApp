package com.example.movieapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.models.MovieModel;
import com.example.movieapp.request.MovieApiClient;

import java.util.List;

public class MovieRepository {


    //Repository


    private static MovieRepository instance;

    private MovieApiClient movieApiClient;



    public static MovieRepository getInstance(){
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository(){
      movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }


    //1 calling the methode in ripo
    public void searchMovieApi(String query, int pageNumber){
        movieApiClient.searchMoviesApi(query,pageNumber);
    }


}



