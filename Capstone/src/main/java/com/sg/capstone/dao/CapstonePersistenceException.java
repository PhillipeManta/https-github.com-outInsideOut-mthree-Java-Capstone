package com.sg.capstone.dao;

/**
 *
 * @author kylerudy
 */
public class CapstonePersistenceException extends Exception{
     public CapstonePersistenceException(String message) {
        super(message);
    }

    public CapstonePersistenceException(String message, Throwable inner) {
        super(message, inner);
    }
}
