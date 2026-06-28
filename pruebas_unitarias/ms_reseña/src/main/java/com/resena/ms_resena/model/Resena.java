package com.resena.ms_resena.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resenas")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private Integer calificacion;

    private String comentario;

    private LocalDateTime fechaResena;

    @PrePersist
    public void prePersist() {

        this.fechaResena = LocalDateTime.now();
    }
}
