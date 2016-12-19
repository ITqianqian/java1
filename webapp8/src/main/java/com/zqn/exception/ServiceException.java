package com.zqn.exception;

/**
 * Created by dell on 2016/12/19.
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable th) {
        super(message, th);
    }

    public ServiceException(Throwable th) {
        super(th);
    }

}

