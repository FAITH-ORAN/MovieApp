package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.movieapp.adapters.MovieRecyclerView;
import com.example.movieapp.adapters.OnMovieListner;
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

public class MovieList extends  AppCompatActivity implements OnMovieListner {

    //RecyclerView
    private RecyclerView recyclerView;

    private MovieRecyclerView movieRecyclerViewAdapter;
     // viewmodel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Button myButton = (Button) findViewById(R.id.button);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SearchView
        SetupSearchView();

        recyclerView =findViewById(R.id.recyclerView);
        //vModel
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        // calling the observer
        ObserverAnyChange();

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
                        movieRecyclerViewAdapter.setmMovies(movieModels);
                    }
                }
            }
        });

    }



    // initializing recyclerViewer & add data
     private void ConfigureRecyclerView(){
        movieRecyclerViewAdapter = new MovieRecyclerView(this);
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
     }


    @Override
    public void onMovieClick(int position) {
        //Toast.makeText(this,"The Position" +position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryClick(String category) {

    }
     //get data from searchView & query the api
    private void SetupSearchView() {

        final SearchView  searchView = findViewById(R.id.search_view);
              searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                  @Override
                  public boolean onQueryTextSubmit(String query) {
                      movieListViewModel.searchMovieApi(
                              query,
                              1
                      );
                      return false;
                  }

                  @Override
                  public boolean onQueryTextChange(String newText) {
                      return false;
                  }
              });
    }
}