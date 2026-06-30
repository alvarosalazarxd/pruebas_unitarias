package com.pedido.ms_pedido.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedidoResponseDTO {

    private Long id;

    private Long productoId;

    private String nombreProducto;

    private String cliente;

    private Integer cantidad;

    private Double total;

    private String estado;

    private LocalDateTime fechaPedido;
}
