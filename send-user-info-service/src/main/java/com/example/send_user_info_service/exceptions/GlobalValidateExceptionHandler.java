package com.example.send_user_info_service.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalValidateExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorsResut = new HashMap<>();
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage())
        );
        errorsResut.put("errorMsg", errorMsg.toString());
        return ResponseEntity.badRequest().body(errorsResut);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        Map<String, String> errorsResut = new HashMap<>();
        errorsResut.put("errorMsg",e.getMessage());
        return ResponseEntity.internalServerError().body(errorsResut);
    }
}
