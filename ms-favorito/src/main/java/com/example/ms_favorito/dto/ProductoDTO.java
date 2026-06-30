package com.example.ms_favorito.dto;

import lombok.Data;

@Data
public class ProductoDTO {

    private Long id;

    private String nombre;

    private Double precio;

    private Integer stock;
}