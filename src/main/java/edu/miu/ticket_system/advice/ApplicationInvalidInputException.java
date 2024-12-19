package edu.miu.ticket_system.advice;


import edu.miu.ticket_system.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationInvalidInputException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> invalidInputExceptionHandler(MethodArgumentNotValidException ex) {

        Map<String, String> errorsMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                err -> errorsMap.put(err.getField(), err.getDefaultMessage())
        );
        return errorsMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> userNotFoundExceptionHandler(UserNotFoundException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put(ex.getMessage(), ex.getMessage());
        return errorsMap;
    }
}
