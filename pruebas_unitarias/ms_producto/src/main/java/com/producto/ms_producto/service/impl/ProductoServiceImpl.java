package com.producto.ms_producto.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.producto.ms_producto.dto.ProductoRequestDTO;
import com.producto.ms_producto.dto.ProductoResponseDTO;
import com.producto.ms_producto.exception.ResourceNotFoundException;
import com.producto.ms_producto.model.Producto;
import com.producto.ms_producto.repository.ProductoRepository;
import com.producto.ms_producto.service.ProductoService;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl
        implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public ProductoResponseDTO crearProducto(
            ProductoRequestDTO requestDTO) {

        Producto producto = new Producto();

        producto.setNombre(
                requestDTO.getNombre());

        producto.setMarca(
                requestDTO.getMarca());

        producto.setCategoria(
                requestDTO.getCategoria());

        producto.setPrecio(
                requestDTO.getPrecio());

        producto.setStock(
                requestDTO.getStock());

        producto.setDescripcion(
                requestDTO.getDescripcion());

        Producto guardado =
                productoRepository.save(producto);

        return convertirDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List <ProductoResponseDTO>
    obtenerTodos() {

        return productoRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO
    obtenerProductoPorId(Long id) {

        Producto producto =
                productoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Producto no encontrado"));

        return convertirDTO(producto);
    }

    @Override
    public ProductoResponseDTO actualizarProducto(
            Long id,
            ProductoRequestDTO requestDTO) {

        Producto producto =
                productoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Producto no encontrado"));

        producto.setNombre(
                requestDTO.getNombre());

        producto.setMarca(
                requestDTO.getMarca());

        producto.setCategoria(
                requestDTO.getCategoria());

        producto.setPrecio(
                requestDTO.getPrecio());

        producto.setStock(
                requestDTO.getStock());

        producto.setDescripcion(
                requestDTO.getDescripcion());

        Producto actualizado =
                productoRepository.save(producto);

        return convertirDTO(actualizado);
    }

    @Override
    public void eliminarProducto(Long id) {

        Producto producto =
                productoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Producto no encontrado"));

        productoRepository.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO>
    buscarPorCategoria(String categoria) {

        return productoRepository.findByCategoria(categoria)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO>
    buscarPorMarca(String marca) {

        return productoRepository.findByMarca(marca)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO>
    buscarPorPrecio(Double precio) {

        return productoRepository
                .findByPrecioLessThanEqual(precio)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO>
    buscarPorNombre(String nombre) {

        return productoRepository
                .findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    private ProductoResponseDTO convertirDTO(
            Producto producto) {

        ProductoResponseDTO dto =
                new ProductoResponseDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setMarca(producto.getMarca());
        dto.setCategoria(producto.getCategoria());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setDescripcion(producto.getDescripcion());
        dto.setFechaRegistro(
                producto.getFechaRegistro());

        return dto;
    }
}
