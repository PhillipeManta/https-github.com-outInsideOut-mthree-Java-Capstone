package com.sg.capstone.service;

import com.sg.capstone.models.Content;
import com.sg.capstone.models.Posts;

import java.sql.Date;
import java.util.List;

public interface PostsService {
    
    public void GetContent(Content c);

    public void deletePostsById(int id) throws PostException;

    public List<Posts> getAllPosts(Date dt);

}
