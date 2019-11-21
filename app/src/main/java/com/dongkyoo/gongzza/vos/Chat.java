package com.dongkyoo.gongzza.vos;

import java.util.Date;

/**
 * 작성자 : 이동규
 * 채팅 데이터 클래스
 */
public class Chat {

    private int id;
    private int postId;
    private String senderId;
    private String content;
    private Date sendedAt;

    public Chat() {
    }

    public Chat(int postId, String senderId, String content, Date sendedAt) {
        this.postId = postId;
        this.senderId = senderId;
        this.content = content;
        this.sendedAt = sendedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendedAt() {
        return sendedAt;
    }

    public void setSendedAt(Date sendedAt) {
        this.sendedAt = sendedAt;
    }
}
