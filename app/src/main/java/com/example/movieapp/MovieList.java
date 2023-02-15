package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.movieapp.models.MovieModel;
import com.example.movieapp.request.Servicey;
import com.example.movieapp.response.MovieSearchResponse;
import com.example.movieapp.utils.Credentials;
import com.example.movieapp.utils.MovieApi;
import com.example.movieapp.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieList extends  AppCompatActivity {



     // viewmodel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton = (Button) findViewById(R.id.button);

        //vModel
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        // calling the observer
        ObserverAnyChange();


        // Testing the method

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchMovieApi("fast",4);
            }
        });

    }

    //Observer of data change
    private void ObserverAnyChange(){
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //observing

                if(movieModels != null){
                    for(MovieModel movieModel:movieModels){
                        // Get the data
                        Log.v("Tag","onChanged "+movieModel.getTitle());
                    }
                }
            }
        });

    }

    // calling the method in main activity
    private void searchMovieApi(String query,int pageNumber){
        movieListViewModel.searchMovieApi(query,pageNumber);
    }




    //one movie

}