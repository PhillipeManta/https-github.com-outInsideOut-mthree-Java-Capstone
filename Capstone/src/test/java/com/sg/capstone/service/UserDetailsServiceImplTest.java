package com.sg.capstone.service;

import com.sg.capstone.dao.InvalidIdException;
import com.sg.capstone.dao.RoleDao;
import com.sg.capstone.dao.UserDao;
import com.sg.capstone.models.Role;
import com.sg.capstone.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    public UserDetailsServiceImplTest(){
    }

    @BeforeEach
    public void setUp() {
        List<User> users= userDao.getAllUsers();
        for (User user : users) {
            userDao.deleteUserById(user.getId());
        }

        List<Role> roles = roleDao.getAllRoles();
        for (Role role : roles) {
            roleDao.deleteRoleById(role.getId());
        }
    }

    @Test
    void getUserDetails() throws InvalidIdException, UsernameFoundException {
        Role role = new Role();
        role.setRole("TopUser");
        role = roleDao.addRole(role);

        User user = new User();
        user.setUsername("Username_Test");
        user.setPassword("Password_Test");
        user.setRole(role);
        user = userDao.addUser(user);

        User fromDao = userDetailsService.getUserById(user.getId());
        assertEquals(user, fromDao);

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