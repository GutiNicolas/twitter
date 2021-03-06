package com.gutinicolas.twitter.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tweets")
public class TweetEntity {

    @Id
    private String id;
    private String ownerUsername;
    private String content;
    private String location;
    private int likes;
    private int dislikes;
    private LocalDateTime tweetedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public LocalDateTime getTweetedDate() {
        return tweetedDate;
    }

    public void setTweetedDate(LocalDateTime tweetedDate) {
        this.tweetedDate = tweetedDate;
    }
}
