package com.example.oop_avancerad_1.Entity;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;
    private String text;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public Post(){ }

    public long getId() {
        return postId;
    }

    public void setId(long id) {
        this.postId = id;
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

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
