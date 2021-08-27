package com.sg.capstone.service;

import com.sg.capstone.dao.StaticPageDao;
import com.sg.capstone.models.Content;
import com.sg.capstone.models.Posts;
import com.sg.capstone.models.StaticPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaticPageServiceImpl implements StaticPageService{

    @Autowired
    StaticPageDao dao;

    @Override
    public StaticPage getStaticPageByTitle(String title) throws TitleException{

        if(dao.getStaticPageByTitle(title) == null){
            throw new TitleException("ERROR: There is no static page with that title! Try another one!");
        }
        return dao.getStaticPageByTitle(title);

    }

    @Override
    public StaticPage putStaticPage(StaticPage staticPage) throws TitleException {

        if(dao.getStaticPageByTitle(staticPage.getTitle()) != null){
            throw new TitleException("ERROR: That title has been taken, find another one!");
        }
        return dao.addStaticPage(staticPage);

    }

    @Override
    public String getAllStaticPagesTitles() {
        List<StaticPage> staticPages = dao.getAllStaticPages();
        List<String> staticPagesTitles = new ArrayList<>();
        for(StaticPage page : staticPages){
            staticPagesTitles.add(page.getTitle());
        }
        return staticPagesTitles.toString();
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        return dao.getAllStaticPages();
    }

    @Override
    public void GetContent(Content c) {
        StaticPage sp = new StaticPage();
        sp.setImageURL(c.getImageURL());
        sp.setTitle(c.getTitle());
        sp.setPost(c.getPost());
        dao.addStaticPage(sp);
    }


}
