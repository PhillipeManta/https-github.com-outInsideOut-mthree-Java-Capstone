package com.sg.capstone.service;

public class PostException extends Exception{

    public PostException(String message){
        super(message);
    }

    public PostException(String message, Throwable cause){
        super(message, cause);
    }

}
