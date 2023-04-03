package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.tinkoff.edu.java.scrapper.dto.response.ApiErrorResponse;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
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
