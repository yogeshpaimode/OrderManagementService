package com.eriks.orderservice.exception.handler;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.eriks.orderservice.exception.ErrorDetails;
import com.eriks.orderservice.exception.ValidationException;

/**
 * The Class ValidationExceptionHandler.
 * 
 * @author Yogesh Paimode
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    /**
     * Handle validation exception.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(),
                "Invalid parameters passed!!", ex.getMessage()));
    }

}
