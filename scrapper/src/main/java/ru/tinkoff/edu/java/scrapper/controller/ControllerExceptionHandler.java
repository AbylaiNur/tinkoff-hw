package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.scrapper.dto.response.ApiErrorResponse;

import java.util.Arrays;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({
        MethodArgumentNotValidException.class,
        HttpMessageNotReadableException.class
    })
    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                "Некорректные параметры запроса",
                "400",
                e.getClass().getName(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .toList())
        );
    }
}
