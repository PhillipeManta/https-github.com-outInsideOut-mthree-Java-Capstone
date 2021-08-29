package com.sg.capstone.models;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author kylerudy
 */

/**
 * Posts DTO
 *
 * Maps directly to the Posts table in the database
 * Used to communicate with the database and client side so that
 * users can retrieve posts and add new ones
 *
 * Is a subclass of Content
 */
public class Posts extends Content {

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
