package com.perfumes.perfumelandia.ms_usuarios.dto;

import lombok.Data;

@Data
public class UsuarioRequestDTO {

    private String nombre;

    private String email;

    private String contrasena;

    private String rol;

    private Boolean activo;

    private String sucursal;

}
