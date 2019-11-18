package com.dongkyoo.gongzza.network;

import com.dongkyoo.gongzza.dtos.CourseDto;
import com.dongkyoo.gongzza.vos.Course;
import com.dongkyoo.gongzza.vos.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 작성자 : 이동규
 * 푸시 토큰 관련 서버 통신 모듈
 */
public interface TokenApi {


    @POST("/tokens")
    Call<Token> registerToken(@Body Token token);

}
