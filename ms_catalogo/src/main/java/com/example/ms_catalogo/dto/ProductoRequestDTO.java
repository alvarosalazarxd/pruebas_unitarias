package com.example.ms_catalogo.dto;

import lombok.Data;

@Data
public class ProductoRequestDTO {

    private String nombre;

    private String marca;

    private String categoria;

    private Double precio;

    private Integer stock;

    private String descripcion;
}