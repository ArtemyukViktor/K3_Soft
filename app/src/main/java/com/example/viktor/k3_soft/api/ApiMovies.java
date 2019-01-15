package com.example.viktor.k3_soft.api;


import com.example.viktor.k3_soft.source.pojoMovies.PojoProduct;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMovies {
    @GET("/3/movie/popular?")
    Observable<PojoProduct> getMovies(@Query("api_key") String kay, @Query("page") Integer numPage);

}
