package com.deniel.system;

/**
 * Created by Deniel on 28.05.2016.
 */
public class TmSystemExeption extends  RuntimeException{
    public TmSystemExeption(String message) {
        super(message);
    }

    public TmSystemExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
