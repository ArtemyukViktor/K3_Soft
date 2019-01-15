package com.example.viktor.k3_soft.app;

import android.app.Application;

import com.example.viktor.k3_soft.api.ApiMovies;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static Retrofit retrofit;



    @Override
    public void onCreate() {
        super.onCreate();

         retrofit = new Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// implementation 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    }


    public static ApiMovies getApiMovies() {
        return retrofit.create(ApiMovies.class);

    }
}
