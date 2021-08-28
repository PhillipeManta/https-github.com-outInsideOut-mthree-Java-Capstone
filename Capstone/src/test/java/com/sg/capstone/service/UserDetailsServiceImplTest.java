package com.sg.capstone.service;

import com.sg.capstone.dao.InvalidIdException;
import com.sg.capstone.dao.UserDao;
import com.sg.capstone.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    UserDetailsService userDetailsService;

    public UserDetailsServiceImplTest(){
    }

    @Test
    void getUserDetails() throws InvalidIdException {
        User testUser = new User();
        testUser.setId(25);
        testUser.setUsername("User3");
        testUser.setPassword("password");

        User shouldBeUser3 = userDetailsService.getUserById(25);
        assertNotNull(shouldBeUser3, "Getting 25 should be not null.");
        assertEquals( shouldBeUser3, testUser,
                "User stored under 25 should be User3.");
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setId(25);
        user.setUsername("User2");
        user.setPassword("password");

        try{
            userDetailsService.getUserById(user.getId());
        } catch (InvalidIdException e) {
            fail("User was valid. No exception should have been thrown.");
        }
    }
}