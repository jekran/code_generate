package com.cg.extra;

public class PreventRepeatException extends RuntimeException {
    public PreventRepeatException() {
        super();
    }


    public PreventRepeatException(String message, Throwable cause) {
        super(message, cause);
    }


    public PreventRepeatException(String message) {
        super(message);
    }


    public PreventRepeatException(Throwable cause) {
        super(cause);
    }
}
