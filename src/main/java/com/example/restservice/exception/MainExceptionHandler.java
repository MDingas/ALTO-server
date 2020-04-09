package com.example.restservice.exception;

import com.example.restservice.dto.ErrorMessageDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.ws.rs.NotFoundException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorMessageDTO handleAllOtherExceptions(Exception ex) {
        return new ErrorMessageDTO(INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public ErrorMessageDTO handleResourceNotFoundException(Exception ex) {
        return new ErrorMessageDTO(NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler({MultipleInformationResourceDirectoriesException.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorMessageDTO handleMultipleInformationResourceDirectoriesException(Exception ex) {
        return new ErrorMessageDTO(INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
