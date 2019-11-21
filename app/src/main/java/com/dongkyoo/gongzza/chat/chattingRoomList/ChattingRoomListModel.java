package com.dongkyoo.gongzza.chat.chattingRoomList;

import com.dongkyoo.gongzza.Utils;
import com.dongkyoo.gongzza.network.ChatApi;
import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.vos.Chat;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ChattingRoomListModel {

    private ChatApi chatApi;

    public ChattingRoomListModel() {
        chatApi = Networks.retrofit.create(ChatApi.class);
    }

    /**
     * DB에 저장된 채팅 로그 불러오기
     * @param postId        불러올 채팅 그룹(post) 아이디
     * @param afterDate     해당 날짜 이후의 데이터만 불러옴
     * @param callback      콜백 메소드
     */
    void loadLocalChatLog(int postId, Date afterDate, Callback<List<Chat>> callback) {

    }


    /**
     * 서버에 저장된 최신 채팅 로그 불러오기
     * @param postId        불러올 채팅 그룹(post) 아이디
     * @param afterDate     해당 날짜 이후의 데이터만 불러옴
     * @param callback      콜백 메소드
     */
    void loadRemoteChatLog(int postId, Date afterDate, Callback<List<Chat>> callback) {
        Call<List<Chat>> call = chatApi.selectChatLogByPostAfterDatetime(postId, Utils.convertDateToString(afterDate));
        call.enqueue(callback);
    }
}
