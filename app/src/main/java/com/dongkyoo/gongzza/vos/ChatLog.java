package com.dongkyoo.gongzza.vos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * 작성자 : 이동규
 * 채팅 데이터 클래스
 */
@Entity
public class ChatLog {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "post_id")
    private int postId;

    @ColumnInfo(name = "sender_id")
    private String senderId;

    @ColumnInfo(name = "sender_name")
    private String senderName;

    @ColumnInfo
    private String content;

    @ColumnInfo(name = "sent_at")
    private Date sentAt;

    public ChatLog() {
    }

    @Ignore
    public ChatLog(int postId, String senderId, String content, Date sentAt) {
        this.postId = postId;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
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

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
