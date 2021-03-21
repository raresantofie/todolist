package com.todlist.todlist.config;

import com.todlist.todlist.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice - prinde exceptiile din backend si le transforma in mesaje sugestive pentru utilizatoir
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 200 - SUCCESS
     * 300 - REDIRECT
     * 400 - CLIENT SIDE ERROR
     * 500 - BACKEND ERROR
     */
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        return errorMessage;
    }


}
