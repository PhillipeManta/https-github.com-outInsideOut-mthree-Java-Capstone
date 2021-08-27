package com.sg.capstone.service;

import com.sg.capstone.models.StaticPage;

import java.util.List;

public interface StaticPageService {

    public StaticPage getStaticPageByTitle(String title) throws TitleException;

    public StaticPage putStaticPage(StaticPage staticPage) throws TitleException;

    public String getAllStaticPagesTitles();

    public List<StaticPage> getAllStaticPages();

}
