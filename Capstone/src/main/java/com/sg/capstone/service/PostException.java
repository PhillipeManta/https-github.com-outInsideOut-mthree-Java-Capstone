package com.sg.capstone.service;

/**
 * Exception handling class
 */
public class PostException extends Exception{

    public PostException(String message){
        super(message);
    }

    public PostException(String message, Throwable cause){
        super(message, cause);
    }

}
