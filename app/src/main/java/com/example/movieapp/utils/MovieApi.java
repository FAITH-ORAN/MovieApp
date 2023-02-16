package com.example.movieapp.utils;

import com.example.movieapp.models.MovieModel;
import com.example.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    // Get Movie
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );

   //get poplar movie
    //https://api.themoviedb.org/3/movie/popular?api_key=a6211b6e7a381b9918f11ab74abc96ae&page=50
    @GET("3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page
    );


     //by Id
    //https://api.themoviedb.org/3/movie/550?api_key=a6211b6e7a381b9918f11ab74abc96ae

    @GET("3/movie/{id}?")
    Call<MovieModel> getMovie(
            @Path("id" )int id,
            @Query("api_key") String key
    );

}
