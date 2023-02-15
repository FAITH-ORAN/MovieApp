package com.example.movieapp.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.AppExcutors;
import com.example.movieapp.models.MovieModel;
import com.example.movieapp.response.MovieSearchResponse;
import com.example.movieapp.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    //live Data
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    //Global Request
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    public static MovieApiClient getInstance(){
        if(instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();
    }


    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }



    //we are going to call this methode through the classes
    public void searchMoviesApi( String query, int pageNumber){
        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future myHandler = AppExcutors.getInstance().netWorkIO().submit(retrieveMoviesRunnable);

        AppExcutors.getInstance().netWorkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // cancel retrofit call if i dont have a resp in3s i cancel the req
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);

    }

        // retreiving data from RestAPi by runable class
        // with query the id & movie

        private class  RetrieveMoviesRunnable implements Runnable{

        private String query;
        private int pageNumber; //for displaying result
        boolean cancelRequest;


            public RetrieveMoviesRunnable(String query, int pageNumber) {
                this.query = query;
                this.pageNumber = pageNumber;
                cancelRequest = false;
            }

            @Override
            public void run(){

                // get the rep obj
                  try{
                      Response response = getMovies(query,pageNumber).execute();

                      if(cancelRequest){
                          return;
                      }
                      if(response.code()== 200){
                          List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                          if(pageNumber != 0){
                              // send data to live data
                              //postValue:used for background thread
                              // setValue not of background

                              mMovies.postValue(list);
                          }else{

                              List<MovieModel> currentMovies = mMovies.getValue();
                              currentMovies.addAll(list);
                              mMovies.postValue(currentMovies);
                          }
                      }else{
                        String error =response.errorBody().string();
                        Log.v("Tag","Error"+error);
                        mMovies.postValue(null);
                      }
                  } catch (IOException e) {
                      e.printStackTrace();
                      mMovies.postValue(null);
                  }



            }



            //search Meyhod query

                private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
                    return Servicey.getMovieApi().searchMovie(
                            Credentials.API_KEY,
                            query,
                            pageNumber

                    );
                }
                private void cancelRequest(){
                    Log.v("Tag", "cancel search Request");
                    cancelRequest = true;
                }




         }








}
