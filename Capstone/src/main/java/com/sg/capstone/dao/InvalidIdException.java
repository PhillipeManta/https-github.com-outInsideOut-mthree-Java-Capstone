package com.sg.capstone.dao;

/**
 *
 * @author kylerudy
 */
public class InvalidIdException extends Exception {

    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException(String message, Throwable inner) {
        super(message, inner);
    }
    
}