package com.sg.capstone.controller;

import com.sg.capstone.models.StaticPage;
import com.sg.capstone.models.User;
import com.sg.capstone.service.StaticPageService;
import com.sg.capstone.service.TitleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StaticPageController {

    @Autowired
    StaticPageService staticPageService;

    @GetMapping("GetStaticPages")
    public String getAllStaticPages(Model model) {
        List<StaticPage> staticPages = staticPageService.getAllStaticPages();
        model.addAttribute("GetStaticPages", staticPages);
        return "GetStaticPages";
    }

    @PostMapping("PutStaticPage")
    public String putStaticPost(HttpServletRequest request) throws TitleException {
        String title = request.getParameter("title");
        String imageURL = request.getParameter("imageURL");
        String post = request.getParameter("post");

        StaticPage staticPage = new StaticPage();
        staticPage.setTitle(title);
        staticPage.setImageURL(imageURL);
        staticPage.setPost(post);

        staticPageService.putStaticPage(staticPage);

        return "redirect:/GetStaticPages";
    }

}
