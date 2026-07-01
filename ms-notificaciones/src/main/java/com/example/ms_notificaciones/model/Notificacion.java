package com.example.ms_notificaciones.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Boolean leida = false;

    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {

        fechaCreacion =
                LocalDateTime.now();
    }
}