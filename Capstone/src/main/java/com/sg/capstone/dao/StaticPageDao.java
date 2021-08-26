/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.dao;

import com.sg.capstone.models.StaticPage;
import java.util.List;

/**
 *
 * @author Raluca
 */
public interface StaticPageDao {

    StaticPage getStaticPageByTitle(String title);
    List<StaticPage> getAllStaticPages();
    StaticPage addStaticPage(StaticPage staticPage);
    void updateStaticPage(StaticPage staticPage);
    void deleteStaticPageByTitle(String title);

}