package com.example.ms_notificaciones.service;

import com.example.ms_notificaciones.dto.NotificacionDTO;

import java.util.List;

public interface NotificacionService {

    NotificacionDTO crearNotificacion(
            NotificacionDTO dto);

    NotificacionDTO buscarPorId(Long id);

    List<NotificacionDTO> listarTodas();

    List<NotificacionDTO>
    buscarPorUsuario(Long usuarioId);

    List<NotificacionDTO>
    buscarPorEstado(Boolean leida);

    NotificacionDTO marcarComoLeida(Long id);

    void eliminar(Long id);
}