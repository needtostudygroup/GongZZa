package com.dongkyoo.gongzza.vos;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Post {

    private int id;
    private String title;
    private String content;
    private Date startAt;
    private Date endAt;
    private Date createdAt;
    private int totalNumOfParticipants;
    private int currentNumOfParticipants;
    private String titleImageUrl;
    private List<HashTag> hashTagList;

    public Post() {
    }

    public Post(int id, String title, String content, Date startAt, Date endAt, Date createdAt, int totalNumOfParticipants, int currentNumOfParticipants, String titleImageUrl, List<HashTag> hashTagList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
        this.createdAt = createdAt;
        this.totalNumOfParticipants = totalNumOfParticipants;
        this.currentNumOfParticipants = currentNumOfParticipants;
        this.titleImageUrl = titleImageUrl;
        this.hashTagList = hashTagList;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getStartAt() {
        return startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getTotalNumOfParticipants() {
        return totalNumOfParticipants;
    }

    public int getCurrentNumOfParticipants() {
        return currentNumOfParticipants;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public List<HashTag> getHashTagList() {
        return hashTagList;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", createdAt=" + createdAt +
                ", totalNumOfParticipants=" + totalNumOfParticipants +
                ", currentNumOfParticipants=" + currentNumOfParticipants +
                ", titleImageUrl='" + titleImageUrl + '\'' +
                ", hashTagList=" + hashTagList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return id == post.id &&
                totalNumOfParticipants == post.totalNumOfParticipants &&
                currentNumOfParticipants == post.currentNumOfParticipants &&
                Objects.equals(title, post.title) &&
                Objects.equals(content, post.content) &&
                Objects.equals(startAt, post.startAt) &&
                Objects.equals(endAt, post.endAt) &&
                Objects.equals(createdAt, post.createdAt) &&
                Objects.equals(titleImageUrl, post.titleImageUrl) &&
                Objects.equals(hashTagList, post.hashTagList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, startAt, endAt, createdAt, totalNumOfParticipants, currentNumOfParticipants, titleImageUrl, hashTagList);
    }
}
