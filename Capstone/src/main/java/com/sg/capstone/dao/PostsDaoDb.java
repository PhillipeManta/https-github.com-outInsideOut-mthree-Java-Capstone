package com.sg.capstone.dao;

import com.sg.capstone.models.Posts;
import com.sg.capstone.models.Role;
import com.sg.capstone.models.User;
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
public class PostsDaoDb implements PostsDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Posts getPostById(int id) {
        try {
            final String SELECT_POST_BY_ID = "SELECT * FROM posts WHERE postId = ?";
            Posts post = jdbc.queryForObject(SELECT_POST_BY_ID, new PostsMapper(), id);
            post.setUser(getUserForPost(id));
            return post;
        }
        catch(DataAccessException ex){
            return null;
        }
    }

    private User getUserForPost(int id){
        final String SELECT_USER_FOR_POST = "SELECT u.* FROM user u JOIN posts p ON p.userId = u.id WHERE u.id = ?";
        return jdbc.queryForObject(SELECT_USER_FOR_POST, new UserDaoDb.UserMapper(), id);
    }

    //Returns posts from the last month
    @Override
    public List<Posts> getAllPosts() {
        final String SELECT_ALL_POSTS = "SELECT * FROM posts WHERE date > 2021-07-28";
        List<Posts> allPosts = jdbc.query(SELECT_ALL_POSTS, new PostsMapper());
        associateUsersForPosts(allPosts);
        return allPosts;
    }

    @Override
    public List<Posts> getHashtagPosts(String hashtagWord) {
        final String SELECT_ALL_HASHTAG_POSTS = "SELECT * FROM posts WHERE post LIKE '%#" + hashtagWord + "%'";
        List<Posts> allPosts = jdbc.query(SELECT_ALL_HASHTAG_POSTS, new PostsMapper());
        associateUsersForPosts(allPosts);
        return allPosts;
    }

    private void associateUsersForPosts(List<Posts> posts) {
        for (Posts post : posts) {
            post.setUser(getUserForPost(post.getId()));
        }
    }

    @Override
    @Transactional
    public Posts addPost(Posts posts) {
        final String INSERT_POST = "INSERT INTO posts(userId, title, imageURL, post, isPosted, postDate)"
                + "VALUES(?,?,?,?,?,?)";
        jdbc.update(INSERT_POST, posts.getUser().getId(),
                    posts.getTitle(),posts.getImageURL(),posts.getPost(),posts.isPosted(),posts.getDate());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        posts.setId(newId);
        return posts;
    }

    @Override
    public void updatePosts(Posts posts) {
        final String UPDATE_POSTS = "UPDATE posts SET userId = ?, title = ?, imageURL = ?, post = ?, isPosted = ?, postDate=? WHERE postId = ?";
        jdbc.update(UPDATE_POSTS, posts.getUser().getId(), posts.getTitle(), posts.getImageURL(), posts.getPost(), posts.isPosted(), posts.getDate(), posts.getId());
    }

    @Override
    @Transactional
    public void deletePostsById(int id) {
        final String DELETE_POST = "DELETE FROM posts WHERE postId = ?";
        jdbc.update(DELETE_POST, id);
    }

    @Override
    public List<Posts> getAllUpToDatePosts() {
        final String SELECT_ALL_UPTODATE_POSTS = "SELECT * FROM posts";
        List<Posts> allPosts = jdbc.query(SELECT_ALL_UPTODATE_POSTS, new PostsMapper());
        associateUsersForPosts(allPosts);
        return allPosts;
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
