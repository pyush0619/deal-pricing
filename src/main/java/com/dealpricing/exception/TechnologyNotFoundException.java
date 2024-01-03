package com.dealpricing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TechnologyNotFoundException extends RuntimeException{
    public TechnologyNotFoundException(String message){
        super(message);
    }
}
