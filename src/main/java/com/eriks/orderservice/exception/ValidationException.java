package com.eriks.orderservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception {

    private static final long serialVersionUID = -9010494121394761501L;

    public ValidationException() {
        super();
    }

    public ValidationException(String msg) {
        super(msg);
    }

}
