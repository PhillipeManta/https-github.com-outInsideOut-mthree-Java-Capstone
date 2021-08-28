package com.sg.capstone.service;

import com.sg.capstone.dao.PostsDao;
import com.sg.capstone.models.Content;
import com.sg.capstone.models.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService{

    @Autowired
    PostsDao dao;

    @Override
    public void GetContent(Content c) {
        Posts p = new Posts();
        p.setImageURL(c.getImageURL());
        p.setTitle(c.getTitle());
        p.setPost(c.getPost());
        dao.addPost(p);
    }

    @Override
    @Transactional
    public void deletePostsById(int id) throws PostException{
        if (dao.getPostById(id) == null){
            throw new PostException("ERROR: Post doesn t exist!");
        }
        dao.deletePostsById(id);
    }



    @Override
    public PostsDao updatePosts(Posts posts) {
        posts.setPosted(true);
        dao.updatePosts(posts);
        return dao;
    }

    @Override
    public List<Posts> getAllPosts() {
        return dao.getAllPosts();
    }


}
