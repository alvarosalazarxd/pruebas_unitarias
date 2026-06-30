package com.inventario.ms_inventario.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventarioRequestDTO {

    @NotNull
    private Long productoId;

    @NotNull
    private Integer stockActual;

    private Integer stockMinimo;

    private String ubicacion;
}