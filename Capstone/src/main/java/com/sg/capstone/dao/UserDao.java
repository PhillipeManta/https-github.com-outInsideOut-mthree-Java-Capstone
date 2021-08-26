/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.dao;

import com.sg.capstone.models.User;
import java.util.List;

/**
 *
 * @author Raluca
 */
public interface UserDao {

    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User addUser(User user);
    void updateUser(User user);
    void deleteUserById(int id);

}