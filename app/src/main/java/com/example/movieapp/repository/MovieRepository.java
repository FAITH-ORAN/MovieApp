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

    private String mQuery;
    private int mPageNumber;

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


    public LiveData<List<MovieModel>> getPop(){
        return movieApiClient.getMoviesPop();
    }


    
    //1 calling the methode in ripo
    public void searchMovieApi(String query, int pageNumber){
        mQuery = query;
        mPageNumber  = pageNumber;
        movieApiClient.searchMoviesApi(query,pageNumber);
    }

    public void searchMoviePop(int pageNumber){
        mPageNumber  = pageNumber;
        movieApiClient.searchMoviesPop(pageNumber);
    }

    public void searchNextPage(){
        searchMovieApi(mQuery,mPageNumber+1);
    }

}



