package com.dongkyoo.gongzza.network;

import com.dongkyoo.gongzza.dtos.PostDto;
import com.dongkyoo.gongzza.vos.Chat;
import com.dongkyoo.gongzza.vos.Post;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
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
 * 채팅 관련 서버 통신 모듈
 */
public interface ChatApi {

    @GET("/chats/posts/{postId}")
    Call<List<Chat>> selectChatLogByPostAfterDatetime(@Path("postId") int postId, @Query("datetime") String datetime);

    @POST("/chats")
    Call<Chat> sendChat(@Body Chat chat);
}
