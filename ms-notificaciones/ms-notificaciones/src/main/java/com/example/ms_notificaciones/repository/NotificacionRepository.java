package com.example.ms_notificaciones.repository;

import com.example.ms_notificaciones.model.Notificacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository
        extends JpaRepository<Notificacion, Long> {

    List<Notificacion>
    findByUsuarioId(Long usuarioId);

    List<Notificacion>
    findByLeida(Boolean leida);

    List<Notificacion>
    findByTipoIgnoreCase(String tipo);
}