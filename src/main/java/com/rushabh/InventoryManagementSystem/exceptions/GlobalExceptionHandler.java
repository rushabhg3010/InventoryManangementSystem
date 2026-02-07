package com.rushabh.InventoryManagementSystem.exceptions;

import com.rushabh.InventoryManagementSystem.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllException(Exception exception) {
        Response response = Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(Exception exception) {
        Response response = Response.builder().status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameValueRequiredException.class)
    public ResponseEntity<Response> handleNameValueRequiredException(Exception exception) {
        Response response = Response.builder().status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Response> handleInvalidCredentialsException(Exception exception) {
        Response response = Response.builder().status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
