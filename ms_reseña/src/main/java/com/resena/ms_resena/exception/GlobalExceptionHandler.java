package com.resena.ms_resena.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class)

    public ResponseEntity<Map<String, Object>>
    manejarNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity.status(
                HttpStatus.NOT_FOUND)

                .body(Map.of(
                        "timestamp",
                        LocalDateTime.now(),

                        "mensaje",
                        ex.getMessage()
                ));
    }
}
