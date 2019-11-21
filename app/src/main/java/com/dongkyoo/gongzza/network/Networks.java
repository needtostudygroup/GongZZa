package com.dongkyoo.gongzza.network;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Networks {

    public static String SERVER_URL = "http://114.206.137.114:8080";
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(JacksonConverterFactory.create(
                    new ObjectMapper().setDateFormat(
                            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssSSS", Locale.KOREA)
                    )))
            .build();

    static {
        try {
            InputStream is = new FileInputStream("src/main/assets/server.properties");
            Properties properties = new Properties();
            properties.load(is);
            SERVER_URL = properties.getProperty("serverUrl");
            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(JacksonConverterFactory.create(
                            new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                    ))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Retrofit createRetrofit(Context context) {
        try {
            InputStream is = context.getAssets().open("server.properties");
            Properties properties = new Properties();
            properties.load(is);
            SERVER_URL = properties.getProperty("serverUrl");
            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(JacksonConverterFactory.create(
                            new ObjectMapper().setDateFormat(
                                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssSSS", Locale.KOREA)
                            )))
                    .build();
            return retrofit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
