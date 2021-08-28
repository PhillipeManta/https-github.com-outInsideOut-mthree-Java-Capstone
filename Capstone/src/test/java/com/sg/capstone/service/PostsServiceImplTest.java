package com.sg.capstone.service;

import com.sg.capstone.models.Posts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsServiceImplTest {

    public PostsServiceImplTest(){
    }

    @Autowired
    PostsServiceImpl postsService;

    @Test
    void deletePostsById() throws PostException {
        Posts postsClone = new Posts();
        postsClone.setId(100);
        postsClone.setPosted(true);
        postsClone.setTitle("Food");
        postsClone.setImageURL("URL");

        postsService.deletePostsById(100);
        assertNull(postsClone,"Removing post with the 100 id should be null." );
    }

    @Test
    void updatePosts() {
        Posts posts1 = new Posts();
        posts1.setId(100);
        posts1.setPosted(false);
        posts1.setTitle("Food");
        posts1.setImageURL("URL");

        postsService.updatePosts(posts1);
        assertEquals(posts1.isPosted(), true, "Update was succesfull");
    }

    @Test
    void getAllPosts() {
        Posts posts1 = new Posts();
        posts1.setId(100);
        posts1.setPosted(false);
        posts1.setTitle("Food");
        posts1.setImageURL("URL");

        assertEquals( 1, postsService.getAllPosts().size(),
                "Should only have one post.");
        assertTrue( postsService.getAllPosts().contains(posts1),
                "The one post should be post1.");

    }
}