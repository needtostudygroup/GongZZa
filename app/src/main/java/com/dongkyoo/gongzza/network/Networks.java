package com.dongkyoo.gongzza.network;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Networks {

    public static String SERVER_URL;

    static {
        try {
            InputStream is = new FileInputStream("src/main/assets/server.properties");
            Properties properties = new Properties();
            properties.load(is);
            SERVER_URL = properties.getProperty("serverUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

}
