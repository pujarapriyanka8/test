package com.test.test;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    String Apikey = "9a0c8e375ada4198a26f7a52638c4b78";
    public ArrayList<articles> newsdata;
    Adapter adapter;

    RecyclerView rv_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // initRecycler();
        rv_news=(RecyclerView)findViewById(R.id.rv_news);
        new MainPresenter(MainActivity.this,MainActivity.this).news();







    }



    }

