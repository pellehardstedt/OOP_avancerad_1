package com.example.oop_avancerad_1.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;
    private String text;
    private java.sql.Timestamp timestamp;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public Post(){
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }
}
