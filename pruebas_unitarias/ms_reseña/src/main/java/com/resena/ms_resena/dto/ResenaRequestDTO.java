package com.resena.ms_resena.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class ResenaRequestDTO {

    @NotNull
    private Long productoId;

    @NotBlank
    private String usuario;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer calificacion;

    private String comentario;
}
