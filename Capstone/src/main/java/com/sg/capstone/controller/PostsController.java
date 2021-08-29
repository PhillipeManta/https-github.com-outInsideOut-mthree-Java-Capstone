/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.controller;

import com.sg.capstone.dao.PostsDao;
import com.sg.capstone.dao.UserDao;
import com.sg.capstone.models.Posts;
import com.sg.capstone.models.StaticPage;
import com.sg.capstone.service.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author fionn
 */

/**
 * Controller that deals with the handling of displaying posts
 * in the index.html file.
 */
@Controller
public class PostsController {

    private String hashtag;
    @Autowired
    StaticPageService staticPageService;
    @Autowired
    PostsService postsService;

    @Autowired
    PostsDao postsDao;

    @Autowired
    UserDao userDao;

    /**
     * Method to retrieve all posts via the posts service.
     * This is then written to the model and access via thyme leaf
     * in the index.html file to print on the webpage
     * @param model
     * @return
     */
    @RequestMapping(value={"/", "/home", "/index.html"}, method= RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<Posts> posts = postsService.getAllPosts();
        model.addAttribute("BlogPosts", posts);
        List<StaticPage> staticPages = staticPageService.getAllStaticPages();
        model.addAttribute("GetStaticPages", staticPages);
        return "index";
    }

    @GetMapping("BlogPosts")
//    public String displayPosts(Model model) {
//        List<Posts> posts = postsService.getAllPosts();
//        model.addAttribute("BlogPosts", posts);
//        return "BlogPosts";
//    }


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
    public String getHashtag(HttpServletRequest request) {
        this.hashtag = request.getParameter("getHashtag");
        return this.hashtag;
    }
    
    @GetMapping("FilterdPosts") 
    public String filteredPosts(Model model, HttpServletRequest request) {
        PostsController pc = new PostsController();
        String hashtag = pc.getHashtag(request);
        List<Posts> posts = postsDao.getHashtagPosts(hashtag);
        model.addAttribute("FilterdPosts", posts);
        return "FilterdPosts";
    }

}
