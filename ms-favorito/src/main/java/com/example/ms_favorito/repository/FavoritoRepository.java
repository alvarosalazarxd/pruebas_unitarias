package com.example.ms_favorito.repository;

import com.example.ms_favorito.model.Favorito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository
        extends JpaRepository<Favorito, Long> {

    List<Favorito> findByUsuarioId(
            Long usuarioId);
}