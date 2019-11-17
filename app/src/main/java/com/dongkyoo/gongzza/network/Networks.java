package com.dongkyoo.gongzza.network;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Networks {

    public static final String SERVER_URL = "http://114.206.137.114:8080";

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
}
