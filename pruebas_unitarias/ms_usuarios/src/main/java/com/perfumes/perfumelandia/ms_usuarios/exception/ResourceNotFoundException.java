package com.perfumes.perfumelandia.ms_usuarios.exception;


public class ResourceNotFoundException 
        extends RuntimeException {

    public ResourceNotFoundException(
            String mensaje) {

        super(mensaje);
    }

}
