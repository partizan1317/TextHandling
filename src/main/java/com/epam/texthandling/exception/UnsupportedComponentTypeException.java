package com.epam.texthandling.exception;

public class UnsupportedComponentTypeException extends Exception {

    public UnsupportedComponentTypeException () {
    }

    public UnsupportedComponentTypeException(String message) {
        super(message);
    }

    public UnsupportedComponentTypeException(Throwable cause) {
        super(cause);
    }

    public UnsupportedComponentTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
