/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.dao;

import com.sg.capstone.models.Posts;
import com.sg.capstone.models.Role;
import com.sg.capstone.models.User;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Raluca
 */
@SpringBootTest
public class PostsDaoDbTest {
    
    @Autowired
    RoleDao roleDao;
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    PostsDao postsDao;
    
    public PostsDaoDbTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
       /* List<Role> roles = roleDao.getAllRoles();
        for (Role role : roles) {
            roleDao.deleteRoleById(role.getId());
        }
        
         List<User> users= userDao.getAllUsers();
        for (User user : users) {
            userDao.deleteUserById(user.getId());
        }
        
         List<Posts> posts= postsDao.getAllPosts();
        for (Posts post : posts) {
            postsDao.deletePostsById(post.getId());
        }*/
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    @Test
    public void testAddAndGetPosts() {
        /*Role role = new Role();
        role.setRole("TopUser");
        role = roleDao.addRole(role);
        
        User user = new User();
        user.setUsername("Username_Test");
        user.setPassword("Password_Test");
        user.setRole(role);
        user = userDao.addUser(user);
        
        Posts post = new Posts();
        post.setUser(user);
        post.setTitle("Title_test");
        post.setImageURL("url_test");
        post.setPost("Post_test");
        post.setPosted(false);
        post = postsDao.addPost(post);*/
        
        Posts post = new Posts();

        List<Posts> fromDao = postsDao.getAllPosts();

        assertEquals(fromDao.size(), 6);
    }
    
}
