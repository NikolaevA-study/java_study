package com.example.DBTestApp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MessageNotFoundException.class)
    protected ResponseEntity<CustomExcept> handleMessageNotFoundExceptionException() {
        return new ResponseEntity<>(new CustomExcept("Сообщение не найдено",400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageExistException.class)
    protected ResponseEntity<CustomExcept> handleMessageExistExceptionException() {
        return new ResponseEntity<>(new CustomExcept("Сообщение с таким айди уже существует",400), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class CustomExcept {
        private String errMsg;
        private Integer status;
    }
}