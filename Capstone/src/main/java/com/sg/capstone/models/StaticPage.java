/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.models;

import java.util.Objects;

/**
 *
 * @author Raluca
 */
public class StaticPage {

    private String title;
    private String imageURL;
    private String post;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaticPage)) return false;
        StaticPage that = (StaticPage) o;
        return Objects.equals(title, that.title) && Objects.equals(imageURL, that.imageURL) && Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, imageURL, post);
    }
}