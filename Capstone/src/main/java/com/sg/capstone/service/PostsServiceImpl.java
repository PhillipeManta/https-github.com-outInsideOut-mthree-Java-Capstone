package com.sg.capstone.service;

import com.sg.capstone.dao.PostsDao;
import com.sg.capstone.dao.StaticPageDao;
import com.sg.capstone.models.Content;
import com.sg.capstone.models.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsServiceImpl implements PostsService{

    @Autowired
    PostsDao postsDao;

    @Autowired
    StaticPageDao staticPageDao;
    
    @Override
    public void putContent(String post) {

    }

    @Override
    public void GetContent(Content c) {
        Posts p = new Posts();
        p.setImageURL(c.getImageURL());
        p.setTitle(c.getTitle());
        p.setPost(c.getPost());
        postsDao.addPost(p);
    }
}
