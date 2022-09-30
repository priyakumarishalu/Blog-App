package com.priya.exceptions;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status,String message){
        this.status=status;
        this.message=message;
    }
    public BlogAPIException(String message, HttpStatus status, String message1){
        super(message);
        status=status;
        message=message1;
    }
    public String getMessage(){
        return this.message;
    }
    public HttpStatus getStatus(){
        return this.status;
    }
}
