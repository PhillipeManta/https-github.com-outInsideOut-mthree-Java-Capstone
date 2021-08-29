package com.sg.capstone.service;

/**
 * Exception handling class
 */
public class TitleException extends Exception{

    public TitleException(String message){
        super(message);
    }

    public TitleException(String message, Throwable cause){
        super(message, cause);
    }

}
