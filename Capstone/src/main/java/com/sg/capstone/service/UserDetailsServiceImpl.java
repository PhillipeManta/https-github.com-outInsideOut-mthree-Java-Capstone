package com.sg.capstone.service;

import com.sg.capstone.dao.InvalidIdException;
import com.sg.capstone.dao.UserDao;
import com.sg.capstone.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService implementation
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserDao dao;

    /**
     * Adds a user according to the user object passed in
     * @param user
     * @return
     * @throws UsernameFoundException
     */
    @Override
    public User loadUserByUsername(User user) throws UsernameFoundException {

        if(dao.getUserByUsername(user.getUsername()) != null){
            throw new UsernameFoundException("ERROR: Could not create a user with that username," +
                    " try another username!");
        }
        return dao.addUser(user);

    }

    /**
     * Retrieves user according to username passed in via the UserDao object
     * @param username
     * @return
     * @throws UsernameFoundException
     */
    @Override
    public User getUserDetails(String username) throws UsernameFoundException {

        if(dao.getUserByUsername(username) != null){
            throw new UsernameFoundException("ERROR: Could not return a user with that username," +
                    " try another username!");
        }
        return dao.getUserByUsername(username);
    }

    /**
     * Retrieves the user according to their ID.
     * @param id
     * @return
     * @throws InvalidIdException
     */
    @Override
    public User getUserById(int id) throws InvalidIdException {
        if(dao.getUserById(id) == null){
            throw new InvalidIdException("ERROR: Could not return a user with that id!");
        }
        return dao.getUserById(id);
    }


}
