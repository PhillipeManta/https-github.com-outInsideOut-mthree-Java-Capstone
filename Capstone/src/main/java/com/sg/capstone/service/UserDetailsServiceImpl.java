package com.sg.capstone.service;

import com.sg.capstone.dao.InvalidIdException;
import com.sg.capstone.dao.UserDao;
import com.sg.capstone.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserDao dao;

    @Override
    public User loadUserByUsername(User user) throws UsernameFoundException {

        if(dao.getUserByUsername(user.getUsername()) != null){
            throw new UsernameFoundException("ERROR: Could not create a user with that username," +
                    " try another username!");
        }
        return dao.addUser(user);

    }


    //asked for
    @Override
    public User getUserDetails(String username) throws UsernameFoundException {

        if(dao.getUserByUsername(username) != null){
            throw new UsernameFoundException("ERROR: Could not return a user with that username," +
                    " try another username!");
        }
        return dao.getUserByUsername(username);
    }

    @Override
    public User getUserById(int id) throws InvalidIdException {
        if(dao.getUserById(id) != null){
            throw new InvalidIdException("ERROR: Could not return a user with that id!");
        }
        return dao.getUserById(id);
    }

    //todo getAllUsers, updateUser,


}
