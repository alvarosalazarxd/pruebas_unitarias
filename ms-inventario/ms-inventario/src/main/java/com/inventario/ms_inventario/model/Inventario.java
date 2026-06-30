package com.inventario.ms_inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;

    @Column(nullable = false)
    private Integer stockActual;

    private Integer stockMinimo;

    private String ubicacion;

    private LocalDateTime fechaActualizacion;

    @PrePersist
    @PreUpdate
    public void actualizarFecha() {

        this.fechaActualizacion =
                LocalDateTime.now();
    }
}