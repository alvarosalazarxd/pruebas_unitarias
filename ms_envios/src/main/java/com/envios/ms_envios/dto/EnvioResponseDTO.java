package com.envios.ms_envios.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EnvioResponseDTO {

    private Long id;
    private Long pedidoId;
    private String direccion;
    private String estado;
    private String codigoSeguimiento;
    private String transportista;
    private LocalDateTime fechaEnvio;
}