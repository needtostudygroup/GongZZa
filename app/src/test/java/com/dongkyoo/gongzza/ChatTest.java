package com.dongkyoo.gongzza;

import com.dongkyoo.gongzza.network.ChatApi;
import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.vos.Chat;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ChatTest {

    @Test
    public void selectChatLogByPostAfterDatetime() throws Exception {
        ChatApi chatApi = Networks.retrofit.create(ChatApi.class);
        Call<List<Chat>> call = chatApi.selectChatLogByPostAfterDatetime(10,
                Utils.convertDateToString(new Date(0)));
        Response<List<Chat>> response = call.execute();
        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

    @Test
    public void sendChat() throws Exception {
        ChatApi chatApi = Networks.retrofit.create(ChatApi.class);
        Call<Chat> call = chatApi.sendChat(new Chat(
                10,
                "testId",
                "test",
                new Date()));
        Response<Chat> response = call.execute();
        assertEquals(200, response.code());
        assertNotNull(response.body());
    }
}
