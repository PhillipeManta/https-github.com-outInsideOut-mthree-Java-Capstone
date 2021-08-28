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


    @PostMapping("/content")
    public String putContent(@ModelAttribute Content content, Model model) {

        if (content.getStaticYN().equals("true")) {
            sPS.GetContent(content);
        }
        else {
            pS.GetContent(content);
        }

        return "redirect:/index.html";

    }

}
