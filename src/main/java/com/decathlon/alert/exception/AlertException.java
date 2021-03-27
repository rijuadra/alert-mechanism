package com.decathlon.alert.exception;

public class AlertException extends RuntimeException {
    public AlertException() {
        super("Exception Occured");
    }

    public AlertException(String msg) {
        super(msg);
    }
}
