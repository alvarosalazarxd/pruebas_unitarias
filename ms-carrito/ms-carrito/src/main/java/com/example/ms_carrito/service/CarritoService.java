package com.example.ms_carrito.service;

import com.example.ms_carrito.dto.CarritoRequestDTO;
import com.example.ms_carrito.dto.CarritoResponseDTO;

import java.util.List;

public interface CarritoService {

    CarritoResponseDTO crearCarrito(
            CarritoRequestDTO requestDTO);

    CarritoResponseDTO obtenerCarritoPorId(
            Long id);

    List<CarritoResponseDTO> obtenerTodosLosCarritos();

    CarritoResponseDTO actualizarCarrito(
            Long id,
            CarritoRequestDTO requestDTO);

    void eliminarCarrito(Long id);
}