package com.deniel.system;

/**
 * Created by Deniel on 28.05.2016.
 */
public class TmSystemException extends RuntimeException{
    public TmSystemException() {
    }

    public TmSystemException(String message) {
        super(message);
    }

    public TmSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
