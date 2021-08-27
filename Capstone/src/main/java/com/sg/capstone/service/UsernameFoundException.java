package com.sg.capstone.service;

public class UsernameFoundException extends Exception{

    public UsernameFoundException(String message){
        super(message);
    }
    public UsernameFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
