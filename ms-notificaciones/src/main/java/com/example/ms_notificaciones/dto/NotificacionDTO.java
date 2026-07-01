package com.example.ms_notificaciones.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacionDTO {

    private Long id;

    private Long usuarioId;

    private String mensaje;

    private String tipo;

    private Boolean leida;

    private LocalDateTime fechaCreacion;
}