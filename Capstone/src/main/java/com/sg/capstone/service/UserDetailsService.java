package com.sg.capstone.service;

import com.sg.capstone.models.User;

public interface UserDetailsService {

    public User loadUserByUsername(User user) throws UsernameFoundException;

}
