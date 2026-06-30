package com.inventario.ms_inventario.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventarioResponseDTO {

    private Long id;

    private Long productoId;

    private String nombreProducto;

    private Integer stockActual;

    private Integer stockMinimo;

    private String ubicacion;

    private LocalDateTime fechaActualizacion;
}