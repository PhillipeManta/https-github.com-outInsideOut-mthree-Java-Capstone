package com.sg.capstone.models;

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
   
}
