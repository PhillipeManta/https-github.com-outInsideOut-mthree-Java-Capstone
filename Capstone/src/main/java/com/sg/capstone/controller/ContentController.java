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

@Controller
public class ContentController {
    @Autowired
    PostsService pS;
    @Autowired
    StaticPageService sPS;

    @RequestMapping(value={"/addBlog.html"}, method= RequestMethod.GET)
    public String displayHomePage(Model model) {
        model.addAttribute("content", new Content());
        List<StaticPage> staticPages = sPS.getAllStaticPages();
        model.addAttribute("GetStaticPages", staticPages);
        return "addBlog";
    }


    @PostMapping("addContent")
    public String putContent(Content content, Model model) {

        try{
            if(!content.getStaticYN().equals(null)) {
                sPS.GetContent(content);
            }
        }catch(Exception e){
            pS.GetContent(content);

        }
//        if (!content.getStaticYN().equals(null)) {
//            sPS.GetContent(content);
//        }
//        else {
//            pS.GetContent(content);
//        }

        return "redirect:/index.html";

    }

}
