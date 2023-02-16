package com.example.movieapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.models.MovieModel;
import com.example.movieapp.repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {
    private MovieRepository movieRepository;

    //constructor
    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }
    public LiveData<List<MovieModel>> getPop(){
        return movieRepository.getPop();
    }

    //2 calling the methode in viewModel

    public void searchMovieApi(String query,int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }
    public void searchMoviePop(int pageNumber){
        movieRepository.searchMoviePop( pageNumber);
    }

    public void searchNextPage(){
        movieRepository.searchNextPage();
    }

}
