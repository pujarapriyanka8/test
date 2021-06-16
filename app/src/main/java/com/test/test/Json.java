package com.test.test;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Json {
    @SerializedName("status")
    public String status;

    @SerializedName("articles")
    public ArrayList<articles> articles;

}
