package com.example.ms_catalogo.service;

import com.example.ms_catalogo.dto.ProductoRequestDTO;
import com.example.ms_catalogo.dto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {

    ProductoResponseDTO crearProducto(
            ProductoRequestDTO requestDTO);

    ProductoResponseDTO obtenerProductoPorId(
            Long id);

    List<ProductoResponseDTO> obtenerTodos();

    void eliminarProducto(Long id);
}