/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.controller;

import com.sg.capstone.models.Content;
import com.sg.capstone.models.Posts;
import com.sg.capstone.service.PostsService;
import com.sg.capstone.service.PostsServiceImpl;
import com.sg.capstone.service.StaticPageService;
import com.sg.capstone.service.StaticPageServiceImpl;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 *
 * @author fionn
 */
@Controller
public class PostsController {

    @Autowired
    PostsService postsService;

    @GetMapping("BlogPosts")
    public String displayPosts(Model model) {
        List<Posts> teachers = postsService.getAllPosts();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }


}
