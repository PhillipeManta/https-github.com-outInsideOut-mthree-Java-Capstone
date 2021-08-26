/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.models;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;


/**
 *
 * @author Raluca
 */
public class Posts {

    private int id;
    private User user;
    private String title;
    private String imageURL;
    private String post;
    private Date date;
    private boolean isPosted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPosted() {
        return isPosted;
    }

    public void setPosted(boolean posted) {
        isPosted = posted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posts)) return false;
        Posts posts = (Posts) o;
        return id == posts.id && isPosted == posts.isPosted && user.equals(posts.user) && Objects.equals(title, posts.title) && Objects.equals(imageURL, posts.imageURL) && post.equals(posts.post) && date.equals(posts.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, imageURL, post, date, isPosted);
    }
}