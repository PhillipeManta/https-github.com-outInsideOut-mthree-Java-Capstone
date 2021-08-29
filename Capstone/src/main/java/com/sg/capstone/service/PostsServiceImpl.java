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

/**
 * Implementation for the PostService.
 */
@Service
public class PostsServiceImpl implements PostsService{

    @Autowired
    PostsDao dao;

    /**
     * Establishes a new post object and sets the details of the
     * post. The PostDao object is then called to add the post
     * @param c
     */
    @Override
    public void GetContent(Content c) {
        Posts p = new Posts();
        p.setImageURL(c.getImageURL());
        p.setTitle(c.getTitle());
        p.setPost(c.getPost());
        dao.addPost(p);
    }

    /**
     * Deletes the post according to an Id given.
     * @param id
     * @throws PostException
     */
    @Override
    @Transactional
    public void deletePostsById(int id) throws PostException{
        if (dao.getPostById(id) == null){
            throw new PostException("ERROR: Post doesn t exist!");
        }
        dao.deletePostsById(id);
    }

    /**
     * Updates a post currently displayed
     * @param posts
     * @return
     */
    @Override
    public PostsDao updatePosts(Posts posts) {
        posts.setPosted(true);
        dao.updatePosts(posts);
        return dao;
    }

    /**
     * retrieves all posts via the Post Dao object.
     * Returns a list.
     * @return
     */
    @Override
    public List<Posts> getAllPosts() {
        return dao.getAllPosts();
    }


}
