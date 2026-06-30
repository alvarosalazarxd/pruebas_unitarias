package com.envios.ms_envios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String estado;

    @Column(unique = true)
    private String codigoSeguimiento;

    private String transportista;

    private LocalDateTime fechaEnvio;

    @PrePersist
    public void generarCodigo() {

        this.codigoSeguimiento =
                "ENV-" + UUID.randomUUID().toString().substring(0,8);

        this.fechaEnvio = LocalDateTime.now();

        if (this.estado == null) {
            this.estado = "PREPARANDO";
        }
    }
}
