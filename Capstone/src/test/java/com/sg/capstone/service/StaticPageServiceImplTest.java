package com.sg.capstone.service;

import com.sg.capstone.models.StaticPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StaticPageServiceImplTest {

    @Autowired
    StaticPageService staticPageService;

    public StaticPageServiceImplTest (){
    }

    @Test
    void getStaticPageByTitle() throws TitleException {
        StaticPage clone = new StaticPage();
        clone.setTitle("post1");
        clone.setImageURL("URL1");
        clone.setPost("Posting about a day in the life of a man");

        StaticPage shouldBePost1 = staticPageService.getStaticPageByTitle(clone.getTitle());
        assertNotNull(shouldBePost1, "Getting post1 should be not null.");
        assertEquals( clone, shouldBePost1,
                "StaticPage stored under post1 title should be post1.");
    }

    @Test
    void putStaticPage() {
        StaticPage clone = new StaticPage();
        clone.setTitle("Dogs Life");
        clone.setImageURL("URL10");
        clone.setPost("Posting about a dog");

        try {
            staticPageService.putStaticPage(clone);
        } catch (TitleException e) {
            fail("StaticPage was valid. No exception should have been thrown.");
        }
    }


    @Test
    void getAllStaticPages() {

        StaticPage clone = new StaticPage();
        clone.setTitle("post2");
        clone.setImageURL("URL2");
        clone.setPost("Posting about anything");

        assertEquals(1, staticPageService.getAllStaticPages().size(),"Should only have one staticPage.");
        assertTrue( staticPageService.getAllStaticPages().contains(clone),
                "The one page should be post1.");
    }

}