package com.sg.capstone.models;

import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author kylerudy
 */
public class Posts {
    private int id;

    private User user;

    private String commentText;
    
    private Set<Role> roles;
    
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
   
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public boolean getIsPosted() {
        return isPosted;
    }

    public void setIsPosted(boolean isPosted) {
        this.isPosted = isPosted;
    }
   
}
