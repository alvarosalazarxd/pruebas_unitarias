package com.pedido.ms_pedido.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;

    @Column(nullable = false)
    private String cliente;

    private Integer cantidad;

    private Double total;

    private String estado;

    private LocalDateTime fechaPedido;

    @PrePersist
    public void prePersist() {

        this.fechaPedido = LocalDateTime.now();

        if (this.estado == null) {

            this.estado = "PENDIENTE";
        }
    }
}