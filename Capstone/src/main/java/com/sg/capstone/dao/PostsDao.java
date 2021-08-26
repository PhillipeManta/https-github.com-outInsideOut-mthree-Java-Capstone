/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.dao;

import com.sg.capstone.models.Posts;
import java.util.List;

/**
 *
 * @author Raluca
 */
public interface PostsDao {

    Posts getPostById(int id);
    List<Posts> getAllPosts();
    Posts addPost(Posts posts);
    void updatePosts(Posts posts);
    void deletePostsById(int id);

}