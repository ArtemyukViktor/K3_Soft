package com.example.viktor.k3_soft.view.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.viktor.k3_soft.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button retry;
    Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        retry = findViewById(R.id.retry);
        check = findViewById(R.id.check);


        retry.setOnClickListener(this);
        check.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.retry : {


                ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

                if( cm.getActiveNetworkInfo() != null){
                    startActivity(new Intent(this,MainActivity.class));
                }
                break;
            }

            case R.id.check : {
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                break;
            }
        }
    }
}
