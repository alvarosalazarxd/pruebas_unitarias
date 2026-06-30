package com.example.ms_catalogo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoResponseDTO {

    private Long id;

    private String nombre;

    private String marca;

    private String categoria;

    private Double precio;

    private Integer stock;

    private String descripcion;
}