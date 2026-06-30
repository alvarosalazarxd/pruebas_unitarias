package com.pedido.ms_pedido.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class PedidoRequestDTO {

    @NotNull
    private Long productoId;

    @NotBlank
    private String cliente;

    @NotNull
    private Integer cantidad;
}