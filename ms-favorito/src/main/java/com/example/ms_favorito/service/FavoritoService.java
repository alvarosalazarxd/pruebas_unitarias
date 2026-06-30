package com.example.ms_favorito.service;

import com.example.ms_favorito.dto.FavoritoDTO;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface FavoritoService {

    FavoritoDTO actualizarFavorito(Long id, FavoritoDTO favoritoDTO);
    
    FavoritoDTO guardarFavorito(
            FavoritoDTO favoritoDTO);

    FavoritoDTO buscarPorId(Long id);

    List<FavoritoDTO> listarFavoritos();

    void eliminarFavorito(Long id);
}