package com.example.ms_favorito.exception;

public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(
            String mensaje) {

        super(mensaje);
    }
}