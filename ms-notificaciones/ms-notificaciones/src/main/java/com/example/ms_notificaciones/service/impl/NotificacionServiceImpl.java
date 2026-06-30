package com.example.ms_notificaciones.service.impl;

import com.example.ms_notificaciones.dto.NotificacionDTO;
import com.example.ms_notificaciones.exception.ResourceNotFoundException;
import com.example.ms_notificaciones.model.Notificacion;
import com.example.ms_notificaciones.repository.NotificacionRepository;
import com.example.ms_notificaciones.service.NotificacionService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificacionServiceImpl
        implements NotificacionService {

    private final NotificacionRepository
            repository;

    @Override
    public NotificacionDTO
    crearNotificacion(
            NotificacionDTO dto) {

        Notificacion notificacion =
                new Notificacion();

        notificacion.setUsuarioId(
                dto.getUsuarioId());

        notificacion.setMensaje(
                dto.getMensaje());

        notificacion.setTipo(
                dto.getTipo());

        Notificacion guardada =
                repository.save(notificacion);

        return convertirDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public NotificacionDTO buscarPorId(
            Long id) {

        Notificacion notificacion =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notificación no encontrada"));

        return convertirDTO(notificacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionDTO>
    listarTodas() {

        return repository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionDTO>
    buscarPorUsuario(Long usuarioId) {

        return repository
                .findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionDTO>
    buscarPorEstado(Boolean leida) {

        return repository
                .findByLeida(leida)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @Override
    public NotificacionDTO
    marcarComoLeida(Long id) {

        Notificacion notificacion =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notificación no encontrada"));

        notificacion.setLeida(true);

        return convertirDTO(
                repository.save(notificacion));
    }

    @Override
    public void eliminar(Long id) {

        Notificacion notificacion =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notificación no encontrada"));

        repository.delete(notificacion);
    }

    private NotificacionDTO convertirDTO(
            Notificacion notificacion) {

        NotificacionDTO dto =
                new NotificacionDTO();

        dto.setId(notificacion.getId());

        dto.setUsuarioId(
                notificacion.getUsuarioId());

        dto.setMensaje(
                notificacion.getMensaje());

        dto.setTipo(
                notificacion.getTipo());

        dto.setLeida(
                notificacion.getLeida());

        dto.setFechaCreacion(
                notificacion.getFechaCreacion());

        return dto;
    }
}