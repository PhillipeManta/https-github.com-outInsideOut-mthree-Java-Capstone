package com.sg.capstone.controller;

import com.sg.capstone.models.Content;
import com.sg.capstone.service.PostsService;
import com.sg.capstone.service.PostsServiceImpl;
import com.sg.capstone.service.StaticPageService;
import com.sg.capstone.service.StaticPageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContentController {

    PostsService pS = new PostsServiceImpl();
    StaticPageService sPS = new StaticPageServiceImpl();

    @RequestMapping(value={"/addBlog.html"}, method= RequestMethod.GET)
    public String displayHomePage(Model model) {
        model.addAttribute("content", new Content());
        return "addBlog";
    }


    @PostMapping("/PutContent")
    public void putContent(HttpServletRequest request, Content c) {
        c.setTitle(request.getParameter("title"));
        c.setImageURL(request.getParameter("imageURL"));
        c.setPost(request.getParameter("post"));
        c.setStaticYN(request.getParameter("staticYN"));

        if (c.getStaticYN().equals("true")) {
            sPS.GetContent(c);
        }
        else {
            pS.GetContent(c);
        }

    }

}
