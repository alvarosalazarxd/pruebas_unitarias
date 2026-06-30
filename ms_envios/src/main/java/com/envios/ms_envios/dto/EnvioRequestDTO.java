package com.envios.ms_envios.dto;


import lombok.Data;

@Data
public class EnvioRequestDTO {

    private Long pedidoId;
    private String direccion;
    private String transportista;
}
