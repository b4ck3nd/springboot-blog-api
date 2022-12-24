package org.springboot.blog.api.exception;

public class UnHandledException extends RuntimeException{
    public UnHandledException() {
        super("unhandled exception");
    }
    public UnHandledException(String msg) {
        super(msg);
    }
}
