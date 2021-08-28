/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.controller;

import com.sg.capstone.dao.PostsDao;
import com.sg.capstone.dao.UserDao;
import com.sg.capstone.models.Content;
import com.sg.capstone.models.Posts;
import com.sg.capstone.service.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *
 * @author fionn
 */
@Controller
public class PostsController {
    
    private String hashtag;
    
    @Autowired
    PostsService postsService;

    @Autowired
    PostsDao postsDao;

    @Autowired
    UserDao userDao;

    @GetMapping("BlogPosts")
    public String displayPosts(Model model) {
        List<Posts> posts = postsService.getAllPosts();
        model.addAttribute("BlogPosts", posts);
        return "BlogPosts";
    }


    @PostMapping("PutBlogPost")
    public String putBlogPost(Posts posts, HttpServletRequest request) {
        String UserId = request.getParameter("userId");

        posts.setUser(userDao.getUserById(Integer.parseInt(UserId)));

        postsDao.addPost(posts);

        return "redirect:/posts";
    }


    @GetMapping("editBlogPost")
    public String editBlogPost(Posts posts, Model model) {
        PostsDao postsDao = postsService.updatePosts(posts);
        model.addAttribute("posts", postsDao);
        return "editBlogPost";
    }


    @GetMapping("deleteBlogPost")
    public String removeBlogPost(Integer id) throws PostException {
        postsService.deletePostsById(id);
        return "redirect:/posts";
    }
    
    @PostMapping("HashTagPosts") 
    public void getHashtag(HttpServletRequest request) {
        this.hashtag = request.getParameter("getHashtag");
    }
}

}
