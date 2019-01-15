package com.example.viktor.k3_soft.source;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.example.viktor.k3_soft.source.pojoMovies.Result;

public class ItemDataSourceFactory extends DataSource.Factory {
    String category;

//    public ItemDataSourceFactory(String category) {
//        this.category = category;
//    }

    private MutableLiveData<PageKeyedDataSource<Integer, Result>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();

//        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Result>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}

