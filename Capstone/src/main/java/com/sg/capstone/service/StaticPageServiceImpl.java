package com.sg.capstone.service;

import com.sg.capstone.dao.StaticPageDao;
import com.sg.capstone.models.Content;
import com.sg.capstone.models.Posts;
import com.sg.capstone.models.StaticPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * StaticPageService implementation
 */
@Service
public class StaticPageServiceImpl implements StaticPageService{

    @Autowired
    StaticPageDao dao;

    /**
     * Retrieves a single static page according to the title given
     * @param title
     * @return
     * @throws TitleException
     */
    @Override
    public StaticPage getStaticPageByTitle(String title) throws TitleException{

        if(dao.getStaticPageByTitle(title) == null){
            throw new TitleException("ERROR: There is no static page with that title! Try another one!");
        }
        return dao.getStaticPageByTitle(title);

    }

    /**
     * Adds a static Page
     * @param staticPage
     * @return
     * @throws TitleException
     */
    @Override
    public StaticPage putStaticPage(StaticPage staticPage) throws TitleException {

        if(dao.getStaticPageByTitle(staticPage.getTitle()) != null){
            throw new TitleException("ERROR: That title has been taken, find another one!");
        }
        return dao.addStaticPage(staticPage);

    }

    /**
     * Returns a list of static page titles.
     * @return
     */
    @Override
    public String getAllStaticPagesTitles() {
        List<StaticPage> staticPages = dao.getAllStaticPages();
        List<String> staticPagesTitles = new ArrayList<>();
        for(StaticPage page : staticPages){
            staticPagesTitles.add(page.getTitle());
        }
        return staticPagesTitles.toString();
    }

    /**
     * Returns a list of all static pages
     * @return
     */
    @Override
    public List<StaticPage> getAllStaticPages() {
        return dao.getAllStaticPages();
    }

    /**
     * Creates a StaticPage object and sets the details needed
     * for when a static page is displayed. adds the staticPage
     * via the StaticPageDao object
     * @param c
     */
    @Override
    public void GetContent(Content c) {
        StaticPage sp = new StaticPage();
        sp.setImageURL(c.getImageURL());
        sp.setTitle(c.getTitle());
        sp.setPost(c.getPost());
        dao.addStaticPage(sp);
    }


}
