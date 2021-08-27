package com.sg.capstone.service;

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
}
