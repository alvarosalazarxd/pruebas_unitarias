package com.resena.ms_resena.service;


import java.util.List;

import com.resena.ms_resena.dto.ResenaRequestDTO;
import com.resena.ms_resena.dto.ResenaResponseDTO;

public interface ResenaService {

    ResenaResponseDTO crearResena(
            ResenaRequestDTO requestDTO);

    List<ResenaResponseDTO> obtenerTodas();

    ResenaResponseDTO obtenerPorId(
            Long id);

    void eliminarResena(
            Long id);
    ResenaResponseDTO actualizarResena(
        Long id,
        ResenaRequestDTO requestDTO);

    List<ResenaResponseDTO> buscarPorProducto(
            Long productoId);

    List<ResenaResponseDTO> buscarPorCalificacion(
            Integer calificacion);

    List<ResenaResponseDTO> buscarPorUsuario(
            String usuario);
        
}