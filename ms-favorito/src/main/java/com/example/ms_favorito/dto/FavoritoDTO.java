package com.example.ms_favorito.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoritoDTO {

    private Long id;

    private Long usuarioId;

    private Long productoId;

    private String nombreProducto;

    private LocalDateTime fechaAgregado;
}