package com.example.viktor.k3_soft.source;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;


import com.example.viktor.k3_soft.Const;
import com.example.viktor.k3_soft.app.App;
import com.example.viktor.k3_soft.source.pojoMovies.PojoProduct;
import com.example.viktor.k3_soft.source.pojoMovies.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ItemDataSource extends PageKeyedDataSource<Integer, Result> {

//String category;

//    public ItemDataSource(String category) {
//        this.category = category;
//    }
// Retrofit retrofit = new Retrofit.Builder()
//        .baseUrl("https://api.themoviedb.org")
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// implementation 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
//        .addConverterFactory(GsonConverterFactory.create())
//        .build();

    //ApiMovies weatherService = retrofit.create(ApiMovies.class);

    private static int counter = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Result> callback) {



        App.getApiMovies().getMovies("2702b01c56f4e0e4df2547b8cbf4dd40",Const.numPage)
        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())// implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
                .subscribe(new Observer<PojoProduct>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PojoProduct pojoWeather) {
                        Log.d("myTag", "onCreate_1: " + pojoWeather.getResults().get(1).getTitle());
                        callback.onResult(pojoWeather.getResults(), null,  Const.numPage+1);


                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("myTag", "onCreate: " +  e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



//        App.appUmorilli
//                .getAnswers(Const.KEY,Const.LIMIT,Const.offset, mParam1, Const.MainImage)//загрузка с 0 лиса по 20
//                .enqueue(new Callback<PojoProduct>() {
//                    @Override
//                    public void onResponse(Call<PojoProduct> call, Response<PojoProduct> response) {
//
//                        if(response.body() != null){
//
//                            callback.onResult(response.body().getResults(), null, Const.offset + 20);
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<PojoProduct> call, Throwable t) {
//                        Log.d("myTag", "onFailure: " + t.getMessage());
//                    }
//                });

 //   }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {
//        App.appUmorilli
//                .getAnswers(Const.KEY,Const.LIMIT,params.key, mParam1,Const.MainImage)
//                .enqueue(new Callback<PojoProduct>() {
//                    @Override
//                    public void onResponse(Call<PojoProduct> call, Response<PojoProduct> response) {
//
//                        if(response.body() != null){
//                            Integer key = (params.key > 1) ? params.key - 20 : null;
//                            callback.onResult(response.body().getResults(), key);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<PojoProduct> call, Throwable t) {
//
//                    }
//                });
        Log.d("newTAg", "loadBefore:  " );

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {




        App.getApiMovies().getMovies("2702b01c56f4e0e4df2547b8cbf4dd40",counter +1)
        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())// implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
                .subscribe(new Observer<PojoProduct>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PojoProduct pojoWeather) {
                       // Integer key = pojoWeather.getResults().size() !=  pojoWeather.getTotalResults() ? params.key + 20 : null;
                        Integer key = pojoWeather.getPage() !=  pojoWeather.getTotalPages() ? params.key + 1 : null;
                        Log.d("newTAg", "onNext: key " + key );
                        Log.d("myTag", "onCreate: " + pojoWeather.getResults().size() + "  "+pojoWeather.getTotalResults());
                        callback.onResult(pojoWeather.getResults(), key);
                        counter ++ ;

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("myTag", "onCreate: " +  e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });



//        App.appUmorilli
//                .getAnswers(Const.KEY,Const.LIMIT,params.key, mParam1,Const.MainImage)
//                .enqueue(new Callback<PojoProduct>() {
//                    @Override
//                    public void onResponse(Call<PojoProduct> call, Response<PojoProduct> response) {
//
//                        if(response.body() != null){
//                            Integer key = response.body().getResults().size() !=  response.body().getCount() ? params.key + 20 : null;
//                            callback.onResult(response.body().getResults(), key);
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<PojoProduct> call, Throwable t) {
//
//                    }
//                });


    }
}

