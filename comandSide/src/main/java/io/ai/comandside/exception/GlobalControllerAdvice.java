package io.ai.comandside.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"io.ai.comandside.controller"})
@Order(100)
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {ApplicationException.class})
    public ResponseEntity<ExceptionResponse> exceptionHandler(ApplicationException exception) {
        return ResponseEntity.ok(new ExceptionResponse(exception.getStatusCode(), exception.getMessage()));
    }
}
