package com.dongkyoo.gongzza.network;

import com.dongkyoo.gongzza.vos.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @GET("/users")
    Call<User> getUserByIdPw(@Query("id") String id, @Query("password") String password);

    @POST("/users")
    Call<User> signUp(@Body User user);
}
