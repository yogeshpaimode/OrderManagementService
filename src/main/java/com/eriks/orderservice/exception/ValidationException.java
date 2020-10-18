package com.eriks.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ValidationException.
 * 
 * @author Yogesh Paimode
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9010494121394761501L;

    /**
     * Instantiates a new validation exception.
     */
    public ValidationException() {
        super();
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param msg the msg
     */
    public ValidationException(String msg) {
        super(msg);
    }

}
