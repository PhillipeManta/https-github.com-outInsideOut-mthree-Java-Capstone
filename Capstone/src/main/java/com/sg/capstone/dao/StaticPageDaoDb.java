package com.sg.capstone.dao;

import com.sg.capstone.models.StaticPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StaticPageDaoDb implements StaticPageDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public StaticPage getStaticPageByTitle(String title) {
        try {
            final String GET_STATICPAGE_BY_TITLE = "SELECT * FROM static WHERE title = ?";
            return jdbc.queryForObject(GET_STATICPAGE_BY_TITLE, new StaticPageMapper(), title);
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        final String GET_ALL_STATICPAGE = "SELECT * FROM static";
        return jdbc.query(GET_ALL_STATICPAGE, new StaticPageMapper());
    }

    @Override
    public StaticPage addStaticPage(StaticPage staticPage) {
        final String INSERT_STATICPAGE = "INSERT INTO static (title, imageURL, post) VALUES(?, ?, ?)";
        jdbc.update(INSERT_STATICPAGE, staticPage.getTitle(), staticPage.getImageURL(), staticPage.getPost());
        return staticPage;
    }

    @Override
    public void updateStaticPage(StaticPage staticPage) {
        final String UPDATE_STATICPAGE = "UPDATE static SET title = ?, imageURL = ?, post = ? WHERE title = ?";
        jdbc.update(UPDATE_STATICPAGE, staticPage.getTitle(), staticPage.getImageURL(), staticPage.getPost());
    }

    @Override
    @Transactional
    public void deleteStaticPageByTitle(String title) {
        final String DELETE_STATIC_PAGE = "DELETE FROM static WHERE title = ?";
        jdbc.update(DELETE_STATIC_PAGE, title);
    }

    public static final class StaticPageMapper implements RowMapper<StaticPage>{

        @Override
        public StaticPage mapRow(ResultSet rs, int index) throws SQLException {
            StaticPage staticPage = new StaticPage();
            staticPage.setTitle(rs.getString("title"));
            staticPage.setImageURL(rs.getString("imageURL"));
            staticPage.setPost(rs.getString("post"));

            return staticPage;
        }
    }
}
