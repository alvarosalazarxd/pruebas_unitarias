package com.example.ms_carrito.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "carritos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Double total = 0.0;

    @Column(name = "cantidad_productos")
    private Integer cantidadProductos = 0;

    @Column(name = "fecha_creacion",
            updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {

        this.fechaCreacion =
                LocalDateTime.now();
    }
}