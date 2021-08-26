package com.sg.capstone.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author kylerudy
 */
public class Posts {

    private int id;
    private User user;
    private String title;
    private String imageURL;
    private String post;
    private LocalDate date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
