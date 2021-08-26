package com.sg.capstone.dao;

import com.sg.capstone.models.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostsDaoDb implements PostsDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Posts getPostById(int id) {
        return null;
    }

    @Override
    public List<Posts> getAllPosts() {
        return null;
    }

    @Override
    public Posts addPost(Posts posts) {
        return null;
    }

    @Override
    public void updatePosts(Posts posts) {

    }

    @Override
    public void deletePostsById(int id) {

    }

    public static final class PostsMapper implements RowMapper<Posts> {

        @Override
        public Posts mapRow(ResultSet rs, int index) throws SQLException {
            Posts posts = new Posts();
            posts.setId(rs.getInt("postId"));
            posts.setTitle(rs.getString("title"));
            posts.setImageURL(rs.getString("imageURL"));
            posts.setPost(rs.getString("post"));
            posts.setPosted(rs.getBoolean("isPosted"));
            posts.setDate(rs.getDate("postDate"));
            return posts;
        }
    }

}
