package com.example.ms_catalogo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La marca no puede estar vacia")
    @Column(nullable = false, length = 100)
    private String marca;

    @NotBlank(message = "La categoria no puede estar vacia")
    @Column(nullable = false, length = 100)
    private String categoria;

    @Positive(message = "El precio debe ser mayor a 0")
    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 500)
    private String descripcion;
}
