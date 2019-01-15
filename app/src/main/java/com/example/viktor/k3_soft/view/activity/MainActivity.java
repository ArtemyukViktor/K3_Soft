package com.example.viktor.k3_soft.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viktor.k3_soft.adapter.ItemAdapter;
import com.example.viktor.k3_soft.model.ItemViewModel;
import com.example.viktor.k3_soft.R;
import com.example.viktor.k3_soft.source.pojoMovies.Result;

import java.util.List;

import static android.support.v4.content.ContextCompat.getSystemService;
import static com.example.viktor.k3_soft.Const.FAIL_VALUE_POSITION;

public class MainActivity extends AppCompatActivity {

    TextView tvWeather;
    List<Result> resultList;
    public RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    ItemViewModel itemViewModel;
    ItemAdapter adapter = null;
    private boolean isNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("myLog", "onCreate: " + this);


        checkNetworkConnected();

        if(!isNetwork){

            startActivity(new Intent(this,Main2Activity.class));


        }else{


            initView();



        setRecyclerFeatures();

        initViewModel();
        Log.d("myLog", "itemViewModel: " + itemViewModel);

        initAdapter();
        observable();

        setRecyclerAdapter();

        }



    }

    private void checkNetworkConnected() {

        isNetwork = isNetworkConnected();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


      int firstVisibleItemPosition =  layoutManager.findFirstVisibleItemPosition();


        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {


            layoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);


        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);

        }
        setRecyclerFeatures();
        setRecyclerAdapter();

        scrollToPosition(firstVisibleItemPosition);


    }

    private void scrollToPosition(int firstVisibleItemPosition) {
        recyclerView.scrollToPosition(firstVisibleItemPosition);
    }

    private void setRecyclerAdapter() {
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();


        Log.d("myLog_10", "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("myLog_10", "onRestart: ");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.d("myLog_10", "onBackPressed: ");
        recyclerView.setVisibility(View.VISIBLE);

    }

    private void setRecyclerFeatures() {

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void initView() {

        recyclerView = findViewById(R.id.Rw);
        layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);

    }

    private void initAdapter() {
        adapter = new ItemAdapter(this);
    }

    private void observable() {
        itemViewModel.getItemPagedList().observe(this, new android.arch.lifecycle.Observer<PagedList<Result>>() {
            @Override
            public void onChanged(@Nullable PagedList<Result> results) {

                adapter.submitList(results);

            }
        });

    }

    private void initViewModel() {
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
    }

    private boolean isNetworkConnected() {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);


            return cm.getActiveNetworkInfo() != null;
    }


}
