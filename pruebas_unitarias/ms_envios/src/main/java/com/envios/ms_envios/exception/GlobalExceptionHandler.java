package com.envios.ms_envios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>>
    manejarResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {

        Map<String, Object> error =
                construirError(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        request.getDescription(false));

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>>
    manejarIllegalArgumentException(
            IllegalArgumentException ex,
            WebRequest request) {

        Map<String, Object> error =
                construirError(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getDescription(false));

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>
    manejarExcepcionGeneral(
            Exception ex,
            WebRequest request) {

        Map<String, Object> error =
                construirError(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),
                        request.getDescription(false));

        return new ResponseEntity<>(
                error,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> construirError(
            HttpStatus estado,
            String mensaje,
            String ruta) {

        Map<String, Object> error =
                new LinkedHashMap<>();

        error.put("timestamp",
                LocalDateTime.now());

        error.put("status",
                estado.value());

        error.put("error",
                estado.getReasonPhrase());

        error.put("mensaje",
                mensaje);

        error.put("ruta",
                ruta.replace("uri=", ""));

        return error;
    }
}
