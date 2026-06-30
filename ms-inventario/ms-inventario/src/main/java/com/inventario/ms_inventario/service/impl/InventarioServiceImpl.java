package com.inventario.ms_inventario.service.impl;



import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.ms_inventario.client.ProductoClient;
import com.inventario.ms_inventario.dto.InventarioRequestDTO;
import com.inventario.ms_inventario.dto.InventarioResponseDTO;
import com.inventario.ms_inventario.dto.ProductoDTO;
import com.inventario.ms_inventario.exception.ResourceNotFoundException;
import com.inventario.ms_inventario.model.Inventario;
import com.inventario.ms_inventario.repository.InventarioRepository;
import com.inventario.ms_inventario.service.InventarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl
        implements InventarioService {

    private final InventarioRepository inventarioRepository;

    private final ProductoClient productoClient;

    @Override
    public InventarioResponseDTO crearInventario(
            InventarioRequestDTO requestDTO) {

        ProductoDTO producto =
                productoClient.obtenerProducto(
                        requestDTO.getProductoId());

        Inventario inventario = new Inventario();

        inventario.setProductoId(
                requestDTO.getProductoId());

        inventario.setStockActual(
                requestDTO.getStockActual());

        inventario.setStockMinimo(
                requestDTO.getStockMinimo());

        inventario.setUbicacion(
                requestDTO.getUbicacion());

        Inventario guardado =
                inventarioRepository.save(inventario);

        return convertirDTO(
                guardado,
                producto.getNombre());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponseDTO>
    obtenerTodos() {

        return inventarioRepository.findAll()
                .stream()
                .map(inventario -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    inventario.getProductoId());

                    return convertirDTO(
                            inventario,
                            producto.getNombre());
                })
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioResponseDTO
    obtenerPorId(Long id) {

        Inventario inventario =
                inventarioRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Inventario no encontrado"));

        ProductoDTO producto =
                productoClient.obtenerProducto(
                        inventario.getProductoId());

        return convertirDTO(
                inventario,
                producto.getNombre());
    }

    @Override
    public InventarioResponseDTO actualizarInventario(
            Long id,
            InventarioRequestDTO requestDTO) {

        Inventario inventario =
                inventarioRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Inventario no encontrado"));

        inventario.setProductoId(
                requestDTO.getProductoId());

        inventario.setStockActual(
                requestDTO.getStockActual());

        inventario.setStockMinimo(
                requestDTO.getStockMinimo());

        inventario.setUbicacion(
                requestDTO.getUbicacion());

        Inventario actualizado =
                inventarioRepository.save(inventario);

        ProductoDTO producto =
                productoClient.obtenerProducto(
                        actualizado.getProductoId());

        return convertirDTO(
                actualizado,
                producto.getNombre());
    }

    @Override
    public void eliminarInventario(Long id) {

        Inventario inventario =
                inventarioRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Inventario no encontrado"));

        inventarioRepository.delete(inventario);
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioResponseDTO
    buscarPorProducto(Long productoId) {

        Inventario inventario =
                inventarioRepository.findByProductoId(
                                productoId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Inventario no encontrado"));

        ProductoDTO producto =
                productoClient.obtenerProducto(
                        inventario.getProductoId());

        return convertirDTO(
                inventario,
                producto.getNombre());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponseDTO>
    stockBajo(Integer stock) {

        return inventarioRepository
                .findByStockActualLessThan(stock)
                .stream()
                .map(inventario -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    inventario.getProductoId());

                    return convertirDTO(
                            inventario,
                            producto.getNombre());
                })
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponseDTO>
    buscarPorUbicacion(String ubicacion) {

        return inventarioRepository
                .findByUbicacionContainingIgnoreCase(
                        ubicacion)
                .stream()
                .map(inventario -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    inventario.getProductoId());

                    return convertirDTO(
                            inventario,
                            producto.getNombre());
                })
                .toList();
    }

    private InventarioResponseDTO convertirDTO(
            Inventario inventario,
            String nombreProducto) {

        InventarioResponseDTO dto =
                new InventarioResponseDTO();

        dto.setId(inventario.getId());

        dto.setProductoId(
                inventario.getProductoId());

        dto.setNombreProducto(
                nombreProducto);

        dto.setStockActual(
                inventario.getStockActual());

        dto.setStockMinimo(
                inventario.getStockMinimo());

        dto.setUbicacion(
                inventario.getUbicacion());

        dto.setFechaActualizacion(
                inventario.getFechaActualizacion());

        return dto;
    }
}
