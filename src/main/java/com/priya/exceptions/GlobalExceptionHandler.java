package com.priya.exceptions;

import com.priya.payLoad.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException
            (ResourceNotFoundException exception, WebRequest webRequest){
    ErrorDetails errorDetails= new ErrorDetails
            (new Date(),exception.getMessage(),webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(BlogAPIException exception,WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails
                (new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllException(Exception exception,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails
                (new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
}

 @Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                              HttpHeaders headers,
                                                              HttpStatus status,
                                                              WebRequest webRequest){
    Map<String,String> errors = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach((error)->{
        String fieldname= ((FieldError)error).getField();
        String message= error.getDefaultMessage();
        errors.put(fieldname,message);
    });
    return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
}

/*@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,WebRequest webRequest){
    Map<String,String> eroors= new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error)->{
        String fieldname=((FieldError)error).getField();
        String msg= error.getDefaultMessage();
        eroors.put(fieldname,msg);
    });
    return new ResponseEntity<>(eroors,HttpStatus.BAD_REQUEST);
}*/
}
