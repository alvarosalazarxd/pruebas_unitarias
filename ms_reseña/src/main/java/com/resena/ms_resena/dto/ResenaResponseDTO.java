package com.resena.ms_resena.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResenaResponseDTO {

    private Long id;

    private Long productoId;

    private String nombreProducto;

    private String usuario;

    private Integer calificacion;

    private String comentario;

    private LocalDateTime fechaResena;
}
