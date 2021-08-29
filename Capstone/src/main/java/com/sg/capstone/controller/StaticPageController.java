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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StaticPageController {

    @Autowired
    StaticPageService staticPageService;

    @RequestMapping(value={"/staticPage.html", "/index.html", "/addBlog.html"}, method= RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<StaticPage> staticPages = staticPageService.getAllStaticPages();
        model.addAttribute("GetStaticPages", staticPages);
        return "staticPage";
    }

    @GetMapping("GetStaticPages")
//    public String getAllStaticPages(Model model) {
//        List<StaticPage> staticPages = staticPageService.getAllStaticPages();
//        model.addAttribute("GetStaticPages", staticPages);
//        return "GetStaticPages";
//    }

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

        return "redirect:/staticPage";
    }


    /*

         @RequestMapping(value={"/staticPage.html"}, method= RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<StaticPage> staticPages = staticPageService.getAllStaticPages();
        model.addAttribute("GetStaticPages", staticPages);
        return "staticPage";
    }

    @GetMapping("GetStaticPages")
    /*public String getAllStaticPages(Model model) {
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
    }*/

}
