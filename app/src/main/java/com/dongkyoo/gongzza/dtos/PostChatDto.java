package com.dongkyoo.gongzza.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import com.dongkyoo.gongzza.ChatList;
import com.dongkyoo.gongzza.vos.ChatLog;
import com.dongkyoo.gongzza.vos.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PostChatDto extends PostDto implements Parcelable {

    private ChatList<ChatLog> chatLogList;

    public PostChatDto() {
    }

    public PostChatDto(ChatList<ChatLog> chatLogList, PostDto postDto) {
        super(postDto);
        this.chatLogList = chatLogList;
    }

    public PostChatDto(Post post) {
        super(post);
        chatLogList = new ChatList<>();
    }

    public PostChatDto(Parcel parcel) {
        super(parcel);
        chatLogList = new ChatList<>();
        parcel.readTypedList(chatLogList, ChatLog.CREATOR);
    }

    public List<ChatLog> getChatLogList() {
        return chatLogList;
    }

    public void setChatLogList(ChatList<ChatLog> chatLogList) {
        this.chatLogList = chatLogList;
    }

    public ChatLog getLatestChatLog() {
        if (chatLogList == null || chatLogList.size() == 0)
            return null;
        return chatLogList.get(chatLogList.size() - 1);
    }

    public void sortChatLogList() {
        Collections.sort(chatLogList);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(chatLogList);
    }

    public static final Creator<PostChatDto> CREATOR = new Creator<PostChatDto>() {
        @Override
        public PostChatDto createFromParcel(Parcel source) {
            return new PostChatDto(source);
        }

        @Override
        public PostChatDto[] newArray(int size) {
            return new PostChatDto[size];
        }
    };
}
