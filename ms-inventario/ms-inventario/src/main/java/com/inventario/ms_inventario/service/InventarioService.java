package com.inventario.ms_inventario.service;



import java.util.List;

import com.inventario.ms_inventario.dto.InventarioRequestDTO;
import com.inventario.ms_inventario.dto.InventarioResponseDTO;

public interface InventarioService {

    InventarioResponseDTO crearInventario(
            InventarioRequestDTO requestDTO);

    List<InventarioResponseDTO> obtenerTodos();

    InventarioResponseDTO obtenerPorId(
            Long id);

    InventarioResponseDTO actualizarInventario(
            Long id,
            InventarioRequestDTO requestDTO);

    void eliminarInventario(
            Long id);

    InventarioResponseDTO buscarPorProducto(
            Long productoId);

    List<InventarioResponseDTO> stockBajo(
            Integer stock);

    List<InventarioResponseDTO> buscarPorUbicacion(
            String ubicacion);
}
