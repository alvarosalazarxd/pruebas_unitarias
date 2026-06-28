package com.producto.ms_producto.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "productos")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String marca;

    private String categoria;

    @Column(nullable = false)
    private Double precio;

    private Integer stock;

    private String descripcion;

    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {

        this.fechaRegistro = LocalDateTime.now();

        if (this.stock == null) {
            this.stock = 0;
        }
    }
}
