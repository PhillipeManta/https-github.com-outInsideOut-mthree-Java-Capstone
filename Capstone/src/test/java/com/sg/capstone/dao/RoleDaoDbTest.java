/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.dao;

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
public class RoleDaoDbTest {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;
    
    public RoleDaoDbTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
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
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetRole() {
        Role role = new Role();
        role.setRole("Admin");
        role = roleDao.addRole(role);

        Role fromDao = roleDao.getRoleById(role.getId());

        assertEquals(role, fromDao);
    }
    
    @Test
    public void testGetAllRoles() {
        Role role = new Role();
        role.setRole("Admin");
        role = roleDao.addRole(role);

        Role role1 = new Role();
        role1.setRole("TopUser");
        role1 = roleDao.addRole(role1);

        List<Role> roles = roleDao.getAllRoles();

        assertEquals(2, roles.size());
        assertTrue(roles.contains(role));
        assertTrue(roles.contains(role1));
    }
    
    @Test
    public void testUpateStudent() {
        Role role = new Role();
        role.setRole("NormalUser");
        role = roleDao.addRole(role);

        Role fromDao = roleDao.getRoleById(role.getId());
        assertEquals(role, fromDao);

        role.setRole("TopUser");
        roleDao.updateRole(role);

        assertNotEquals(role, fromDao);

        fromDao = roleDao.getRoleById(role.getId());

        assertEquals(role, fromDao);
    }
    
    @Test
    public void testDeleteTeacherById() {
        Role role = new Role();
        role.setRole("NormalUser");
        role = roleDao.addRole(role);

        User user = new User();
        user.setUsername("Username_test");
        user.setPassword("Password_test");
        user.setRole(role);
        user = userDao.addUser(user);

        Role fromDao = roleDao.getRoleById(role.getId());
        assertEquals(role, fromDao);

        roleDao.deleteRoleById(role.getId());

        fromDao = roleDao.getRoleById(role.getId());
        assertNull(fromDao);
    }
}
