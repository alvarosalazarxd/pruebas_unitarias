package com.resena.ms_resena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resena.ms_resena.model.Resena;

import java.util.List;

@Repository
public interface ResenaRepository
        extends JpaRepository<Resena, Long> {

    List<Resena> findByProductoId(
            Long productoId);

    List<Resena> findByCalificacion(
            Integer calificacion);

    List<Resena> findByUsuarioContainingIgnoreCase(
            String usuario);
}