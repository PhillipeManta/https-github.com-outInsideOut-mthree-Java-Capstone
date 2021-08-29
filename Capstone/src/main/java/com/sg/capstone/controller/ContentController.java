package com.sg.capstone.controller;

import com.sg.capstone.models.Content;
import com.sg.capstone.models.StaticPage;
import com.sg.capstone.service.PostsService;
import com.sg.capstone.service.PostsServiceImpl;
import com.sg.capstone.service.StaticPageService;
import com.sg.capstone.service.StaticPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller that deals with adding pages to either the Blog Posts page
 * or adding a Static page.
 */
@Controller
public class ContentController {
    @Autowired
    PostsService pS;
    @Autowired
    StaticPageService sPS;

    /**
     * Method called to return an empty content object to the model.
     * This object will be written once the user has entered valid fields
     * in the addBlog.html window.
     *
     * The navigation bar is also set up if the user would like to go to
     * one of the static pages.
     * @param model
     * @return
     */
    @RequestMapping(value={"/addBlog.html"}, method= RequestMethod.GET)
    public String displayHomePage(Model model) {
        model.addAttribute("content", new Content());
        List<StaticPage> staticPages = sPS.getAllStaticPages();
        model.addAttribute("GetStaticPages", staticPages);
        return "addBlog";
    }


    /**
     * Called when the user presses the submit button
     * in the addBlog.html.
     * Writes the new data via the services and daos to
     * create a new record in the database.
     * This should then show up as a static page if the admin chooses to make it like that
     * or it will appear as a blog post (or a pending post awaiting admin approval)
     *
     * redirects the user back to the index.html page
     * @param content
     * @param model
     * @return
     */
    @PostMapping("addContent")
    public String putContent(Content content, Model model) {

        try{
            if(!content.getStaticYN().equals(null)) {
                sPS.GetContent(content);
            }
        }catch(Exception e){
            pS.GetContent(content);

        }
        return "redirect:/index.html";

    }

}
