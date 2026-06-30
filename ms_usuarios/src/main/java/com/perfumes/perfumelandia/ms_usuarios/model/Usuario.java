package com.perfumes.perfumelandia.ms_usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Email(message = "El email debe ser valido")
    @NotBlank(message = "El email no puede estar vacio")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false, length = 50)
    private String rol;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(length = 100)
    private String sucursal;
}
