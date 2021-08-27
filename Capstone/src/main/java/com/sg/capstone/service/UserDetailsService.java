package com.sg.capstone.service;

import com.sg.capstone.dao.InvalidIdException;
import com.sg.capstone.models.User;

public interface UserDetailsService {

    public User loadUserByUsername(User user) throws UsernameFoundException;

    public User getUserDetails(String username) throws UsernameFoundException;

    public User getUserById(int id) throws InvalidIdException;

}
