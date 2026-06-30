package com.example.ms_catalogo.exception;

public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(
            String mensaje) {

        super(mensaje);
    }
}