package com.example.ms_notificaciones.dto;

import lombok.Data;

@Data
public class NotificacionRequestDTO {

    private Long usuarioId;

    private String mensaje;

    private String tipo;
}
