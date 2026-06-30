package com.producto.ms_producto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoRequestDTO {

    @NotBlank
    private String nombre;

    private String marca;

    private String categoria;

    @NotNull
    private Double precio;

    private Integer stock;

    private String descripcion;
}
