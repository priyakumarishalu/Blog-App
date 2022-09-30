package com.priya.payLoad;

import lombok.Data;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp,String message,String details){
        this.timestamp=timestamp;
        this.message=message;
        this.details=details;
    }
    public Date getTimestamp(){
        return this.timestamp;
    }
    public String getDetails(){
        return this.details;
    }
    public String getMessage(){
        return this.message;
    }

}
