package com.example.ms_favorito.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "favoritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Long productoId;

    @Column(nullable = false)
    private String nombreProducto;

    @Column(name = "fecha_agregado")
    private LocalDateTime fechaAgregado;

    @PrePersist
    public void prePersist() {
        this.fechaAgregado = LocalDateTime.now();
    }
}