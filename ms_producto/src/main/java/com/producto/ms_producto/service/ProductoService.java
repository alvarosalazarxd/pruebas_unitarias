package com.producto.ms_producto.service;

import java.util.List;

import com.producto.ms_producto.dto.ProductoRequestDTO;
import com.producto.ms_producto.dto.ProductoResponseDTO;

public interface ProductoService {

    ProductoResponseDTO crearProducto(
            ProductoRequestDTO requestDTO);

    List<ProductoResponseDTO> obtenerTodos();

    ProductoResponseDTO obtenerProductoPorId(
            Long id);

    ProductoResponseDTO actualizarProducto(
            Long id,
            ProductoRequestDTO requestDTO);

    void eliminarProducto(Long id);

    List<ProductoResponseDTO> buscarPorCategoria(
            String categoria);

    List<ProductoResponseDTO> buscarPorMarca(
            String marca);

    List<ProductoResponseDTO> buscarPorPrecio(
            Double precio);

    List<ProductoResponseDTO> buscarPorNombre(
            String nombre);
}