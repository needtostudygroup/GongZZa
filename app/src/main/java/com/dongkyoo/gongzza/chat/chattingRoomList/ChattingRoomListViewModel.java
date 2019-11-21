package com.dongkyoo.gongzza.chat.chattingRoomList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dongkyoo.gongzza.dtos.PostChatDto;

public class ChattingRoomListViewModel extends ViewModel {

    private MutableLiveData<PostChatDto> _postChat = new MutableLiveData<>();

    public LiveData<PostChatDto> postChat = _postChat;

    /**
     * DB에 저장된 채팅 로그
     */
    void loadLocalChatLog() {

    }

    /**
     * 서버에 저장된 최신 채팅 로그 불러오기
     */
    void loadRemoteChatLog() {

    }
}
