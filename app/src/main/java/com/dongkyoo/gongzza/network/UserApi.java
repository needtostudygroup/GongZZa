package com.dongkyoo.gongzza.network;

import com.dongkyoo.gongzza.vos.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    /**
     * 로그인 요청
     * @param id 유저 아이디
     * @param password 유저 비밀번호
     * @return 로그인 성공 시 해당 유저의 정보가 담긴 객체를 리턴
     *      로그인 실패 시 500 에러
     */
    @GET("/users")
    Call<User> getUserByIdPw(@Query("id") String id, @Query("password") String password);

    /**
     * 회원가입 요청
     * @param user 유저 회원 정보가 담긴 User 객체
     * @return 회원가입 성공 시 가입된 User 정보가 담긴 객체 리턴
     */
    @POST("/users")
    Call<User> signUp(@Body User user);
}
