package com.test.test;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MainContract {

    public Context mContext;
    public MainActivity mainActivity;
    public MainPresenter(MainActivity mainActivity, MainActivity mainActivity1) {
    this.mContext=mainActivity;
    this.mainActivity=mainActivity1;
    }

    @Override
    public void news() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final api request = retrofit.create(api.class);
        Call<Json> call = request.news();
        call.enqueue(new Callback<Json>() {
            @Override
            public void onResponse(Call<Json> call, Response<Json> response) {
                Log.d("sdahsgd",response.body().status);
                if(response.body().articles != null && response.body().articles.size() > 0){
                    mainActivity.newsdata=response.body().articles;
                    mainActivity.rv_news.setLayoutManager(new LinearLayoutManager(mContext));
                    mainActivity.adapter = new Adapter(mContext,response.body().articles,mainActivity);
                    mainActivity.rv_news.setAdapter(mainActivity.adapter);

                }
            }

            @Override
            public void onFailure(Call<Json> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
