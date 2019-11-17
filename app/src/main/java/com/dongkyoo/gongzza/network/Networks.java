package com.dongkyoo.gongzza.network;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Networks {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://114.206.137.114:8080")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
}
