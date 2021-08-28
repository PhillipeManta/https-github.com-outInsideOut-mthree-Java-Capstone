package com.sg.capstone.dao;

import com.sg.capstone.models.Posts;

import java.util.List;

public interface PostsDao {

    Posts getPostById(int id);
    List<Posts> getAllPosts();
    List<Posts> getHashtagPosts(String hashtag);
    Posts addPost(Posts posts);
    void updatePosts(Posts posts);
    void deletePostsById(int id);

}
