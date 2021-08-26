package com.sg.capstone.dao;

import com.sg.capstone.models.User;

import java.util.List;

public interface UserDao {

    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User addUser(User user);
    void updateUser(User user);
    void deleteUserById(int id);

}
