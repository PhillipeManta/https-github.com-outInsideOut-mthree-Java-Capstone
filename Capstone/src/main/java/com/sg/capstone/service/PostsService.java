package com.sg.capstone.service;

import com.sg.capstone.dao.PostsDao;
import com.sg.capstone.models.Content;
import com.sg.capstone.models.Posts;
import com.sg.capstone.models.StaticPage;

import java.sql.Date;
import java.util.List;

/**
 * Interface for the PostService
 */
public interface PostsService {
    
    public void GetContent(Content c);

    public void deletePostsById(int id) throws PostException;

    public PostsDao updatePosts(Posts posts);

    List<Posts> getAllPosts();

}
