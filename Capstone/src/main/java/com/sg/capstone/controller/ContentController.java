package com.sg.capstone.controller;

import com.sg.capstone.models.Content;
import com.sg.capstone.service.PostsService;
import com.sg.capstone.service.PostsServiceImpl;
import com.sg.capstone.service.StaticPageService;
import com.sg.capstone.service.StaticPageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContentController {

    PostsService pS = new PostsServiceImpl();
    StaticPageService sPS = new StaticPageServiceImpl();

    @GetMapping("PutContent")
    public void putContent(HttpServletRequest request) {
        Content c = new Content();
        c.setTitle(request.getParameter("title"));
        c.setImageURL(request.getParameter("imageURL"));
        c.setPost(request.getParameter("post"));
        c.setStaticYN(Boolean.parseBoolean(request.getParameter("staticYN")));

        if (c.isStaticYN()) {
            sPS.GetContent(c);
        }
        else {
            pS.GetContent(c);
        }
    }

}
