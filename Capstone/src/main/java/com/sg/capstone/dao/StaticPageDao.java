package com.sg.capstone.dao;

import com.sg.capstone.models.StaticPage;

import java.util.List;

/**
 * Interface for the StaticPage DTO
 */
public interface StaticPageDao {

    StaticPage getStaticPageByTitle(String title);
    List<StaticPage> getAllStaticPages();
    StaticPage addStaticPage(StaticPage staticPage);
    void updateStaticPage(StaticPage staticPage);
    void deleteStaticPageByTitle(String title);

}
