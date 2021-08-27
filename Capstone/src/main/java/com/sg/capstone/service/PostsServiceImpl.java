package com.sg.capstone.service;

import com.sg.capstone.dao.PostsDao;
import com.sg.capstone.dao.StaticPageDao;
import com.sg.capstone.models.StaticPage;
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
}
