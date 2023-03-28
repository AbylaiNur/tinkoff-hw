package ru.tinkoff.edu.java.bot.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.dto.response.ApiErrorResponse;

import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {
        String description = "";
        List<String> stacktrace = List.of("fdsdf", "fdskj");
        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                description, "400", "", "", stacktrace)
        );
    }
}
