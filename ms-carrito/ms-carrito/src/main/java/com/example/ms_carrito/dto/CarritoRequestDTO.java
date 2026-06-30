package com.example.ms_carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarritoRequestDTO {

    private Long usuarioId;

    private Double total;

    private Integer cantidadProductos;
}