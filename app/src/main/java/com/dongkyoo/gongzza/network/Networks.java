package com.dongkyoo.gongzza.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Networks {

    private static String SERVER_URL;

    static {
        try {
            File file = new File("src/main/assets/server.properties");
            System.out.println(file.getAbsolutePath());
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
