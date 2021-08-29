package com.sg.capstone.service;

import com.sg.capstone.dao.PostsDao;
import com.sg.capstone.dao.RoleDao;
import com.sg.capstone.dao.UserDao;
import com.sg.capstone.models.Posts;
import com.sg.capstone.models.Role;
import com.sg.capstone.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsServiceImplTest {

    public PostsServiceImplTest(){
    }


    @Autowired
    PostsServiceImpl postsService;

    @Autowired
    PostsDao postsDao;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @BeforeEach
    public void setUp() {
        List<Role> roles = roleDao.getAllRoles();
        for (Role role : roles) {
            roleDao.deleteRoleById(role.getId());
        }

        List<Posts> posts= postsDao.getAllPosts();
        for (Posts post : posts) {
            postsDao.deletePostsById(post.getId());
        }

        List<User> users= userDao.getAllUsers();
        for (User user : users) {
            userDao.deleteUserById(user.getId());
        }


    }

    @Test
    void deletePostsById() throws PostException {
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
        post = postsDao.addPost(post);

        Posts fromDao = postsDao.getPostById(post.getId());

        postsDao.deletePostsById(post.getId());

        fromDao = postsDao.getPostById(post.getId());
        assertNull(fromDao);
    }

    @Test
    void updatePosts() {
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
        post = postsDao.addPost(post);

        postsService.updatePosts(post);
        assertEquals(post.isPosted(), true, "Update was succesfull");
    }

    @Test
    void getAllPosts() {
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
        post = postsDao.addPost(post);


        assertEquals( 1, postsService.getAllPosts().size(),
                "Should only have one post.");

    }
}