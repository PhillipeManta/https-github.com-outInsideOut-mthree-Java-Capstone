/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.models;

/**
 *
 * @author fionn
 */
public class Content {
    public String title;
    public String post;
    public String imageURL;
    public boolean staticYN;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isStaticYN() {
        return staticYN;
    }

    public void setStaticYN(boolean staticYN) {
        this.staticYN = staticYN;
    }
    
    
}