package com.dongkyoo.gongzza.dtos;

import com.dongkyoo.gongzza.vos.ChatLog;
import com.dongkyoo.gongzza.vos.Post;

import java.util.ArrayList;
import java.util.List;

public class PostChatDto extends PostDto {

    private List<ChatLog> chatLogList;

    public PostChatDto() {
    }

    public PostChatDto(List<ChatLog> chatLogList, PostDto postDto) {
        super(postDto);
        this.chatLogList = chatLogList;
    }

    public PostChatDto(Post post) {
        super();
        chatLogList = new ArrayList<>();
        this.chatLogList = new ArrayList<>();
    }

    public List<ChatLog> getChatLogList() {
        return chatLogList;
    }

    public void setChatLogList(List<ChatLog> chatLogList) {
        this.chatLogList = chatLogList;
    }

    public ChatLog getLatestChatLog() {
        if (chatLogList == null || chatLogList.size() == 0)
            return null;
        return chatLogList.get(chatLogList.size() - 1);
    }
}
