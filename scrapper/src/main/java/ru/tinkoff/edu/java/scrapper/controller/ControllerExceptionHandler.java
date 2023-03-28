package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException(Exception e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                "Некорректные параметры запроса",
                "400",
                "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException",
                e.getMessage(),
                Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .toList()
        );
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }
}
