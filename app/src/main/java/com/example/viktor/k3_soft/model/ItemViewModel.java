package com.example.viktor.k3_soft.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.viktor.k3_soft.adapter.ItemAdapter;
import com.example.viktor.k3_soft.source.ItemDataSourceFactory;
import com.example.viktor.k3_soft.source.pojoMovies.Result;

import static android.support.v4.content.ContextCompat.getSystemService;
import static com.example.viktor.k3_soft.Const.FAIL_VALUE_POSITION;

public class ItemViewModel extends ViewModel {

    private LiveData<PagedList<Result>> itemPagedList;


    public ItemViewModel() {

        Log.d("myViewModel", "ItemViewModel: ");
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        //liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(20)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();

    }

    public LiveData<PagedList<Result>> getItemPagedList() {
        return itemPagedList;
    }

    public void setItemPagedList(LiveData<PagedList<Result>> itemPagedList) {
        this.itemPagedList = itemPagedList;
    }



}
