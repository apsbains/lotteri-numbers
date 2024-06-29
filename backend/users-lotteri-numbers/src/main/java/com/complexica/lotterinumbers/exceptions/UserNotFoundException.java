package com.complexica.lotterinumbers.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

}
