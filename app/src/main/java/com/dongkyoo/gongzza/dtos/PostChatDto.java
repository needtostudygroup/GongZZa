package com.dongkyoo.gongzza.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import com.dongkyoo.gongzza.vos.Chat;
import com.dongkyoo.gongzza.vos.HashTag;
import com.dongkyoo.gongzza.vos.Participant;
import com.dongkyoo.gongzza.vos.Post;
import com.dongkyoo.gongzza.vos.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PostChatDto extends PostDto {

    private List<Chat> chatList;

    public PostChatDto() {
    }

    public PostChatDto(List<Chat> chatList, PostDto postDto) {
        super(postDto);
        this.chatList = chatList;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }
}
