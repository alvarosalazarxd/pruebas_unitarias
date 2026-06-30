package com.example.ms_carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CarritoResponseDTO {

    private Long id;

    private Long usuarioId;

    private Double total;

    private Integer cantidadProductos;

    private LocalDateTime fechaCreacion;
}