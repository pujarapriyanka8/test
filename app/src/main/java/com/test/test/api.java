package com.test.test;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api {

    @GET("https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=9a0c8e375ada4198a26f7a52638c4b78")
    Call<Json> news() ;
}
