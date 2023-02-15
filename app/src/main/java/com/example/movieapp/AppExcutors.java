package com.example.movieapp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class AppExcutors {

    //singelton Pattern
    private static AppExcutors instance;
    public static AppExcutors getInstance(){
        if(instance == null){
            instance  = new AppExcutors();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);


    public ScheduledExecutorService netWorkIO(){
        return mNetworkIO;
    }

}
