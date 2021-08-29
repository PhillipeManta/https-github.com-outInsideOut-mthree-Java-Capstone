/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.dao;

import com.sg.capstone.models.Posts;
import com.sg.capstone.models.Role;
import com.sg.capstone.models.User;
import java.sql.Date;
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
    
    Date date = null;
    
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
        List<Role> roles = roleDao.getAllRoles();
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
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    @Test
    public void testAddAndGetPosts() {
        Role role = new Role();
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
        post.setDate(date);
        post = postsDao.addPost(post);

        Posts fromDao = postsDao.getPostById(post.getId());

        assertEquals(post, fromDao);
    }
    
    
    @Test
    public void testGetAllPosts() {
        Role role = new Role();
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
        post.setDate(date);
        post = postsDao.addPost(post);
        
        Posts post1 = new Posts();
        post1.setUser(user);
        post1.setTitle("Title_test1");
        post1.setImageURL("url_test1");
        post1.setPost("Post_test1");
        post1.setPosted(false);    
        post1.setDate(date);
        post1 = postsDao.addPost(post1);

        List<Posts> posts = postsDao.getAllPosts();

        assertEquals(2, posts.size());
        assertTrue(posts.contains(post));
        assertTrue(posts.contains(post1));
    }
    
    @Test
    public void testUpdatePosts() {
        Role role = new Role();
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
        post.setDate(date);
        post = postsDao.addPost(post);

        Posts fromDao = postsDao.getPostById(post.getId());
        assertEquals(post, fromDao);
        
        post.setTitle("NewTitle_test");
        Role role1 = new Role();
        role1.setRole("NewTopUser");
        role1 = roleDao.addRole(role1);
        User user1 = new User();
        user1.setUsername("NewUsername_Test");
        user1.setPassword("NewPassword_Test");
        user1.setRole(role1);
        user1 = userDao.addUser(user1);
        post.setUser(user1);
        
        postsDao.updatePosts(post);
        assertNotEquals(post, fromDao);
        
        fromDao = postsDao.getPostById(post.getId());
        assertEquals(post, fromDao);
             
    }
    
    
    @Test
    public void testDeletePostById() {
        Role role = new Role();
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
        post.setDate(date);
        post = postsDao.addPost(post);

        Posts fromDao = postsDao.getPostById(post.getId());
        postsDao.deletePostsById(post.getId());

        fromDao = postsDao.getPostById(post.getId());
        assertNull(fromDao);
    }
    
}
