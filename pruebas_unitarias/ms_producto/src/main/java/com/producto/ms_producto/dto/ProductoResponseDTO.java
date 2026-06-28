package com.producto.ms_producto.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductoResponseDTO {

    private Long id;

    private String nombre;

    private String marca;

    private String categoria;

    private Double precio;

    private Integer stock;

    private String descripcion;

    private LocalDateTime fechaRegistro;
}