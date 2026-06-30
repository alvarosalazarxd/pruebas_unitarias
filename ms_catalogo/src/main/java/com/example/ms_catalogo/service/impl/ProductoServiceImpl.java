package com.example.ms_catalogo.service.impl;

import com.example.ms_catalogo.dto.ProductoRequestDTO;
import com.example.ms_catalogo.dto.ProductoResponseDTO;
import com.example.ms_catalogo.exception.ResourceNotFoundException;
import com.example.ms_catalogo.model.Producto;
import com.example.ms_catalogo.repository.ProductoRepository;
import com.example.ms_catalogo.service.ProductoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoServiceImpl
        implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public ProductoResponseDTO crearProducto(
            ProductoRequestDTO requestDTO) {

        Producto producto = new Producto();

        producto.setNombre(requestDTO.getNombre());
        producto.setMarca(requestDTO.getMarca());
        producto.setCategoria(requestDTO.getCategoria());
        producto.setPrecio(requestDTO.getPrecio());
        producto.setStock(requestDTO.getStock());
        producto.setDescripcion(
                requestDTO.getDescripcion());

        Producto guardado =
                productoRepository.save(producto);

        return convertirDTO(guardado);
    }

    @Override
    public ProductoResponseDTO obtenerProductoPorId(
            Long id) {

        Producto producto =
                productoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Producto no encontrado"));

        return convertirDTO(producto);
    }

    @Override
    public List<ProductoResponseDTO> obtenerTodos() {

        return productoRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(Long id) {

        productoRepository.deleteById(id);
    }

    private ProductoResponseDTO convertirDTO(
            Producto producto) {

        return ProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .marca(producto.getMarca())
                .categoria(producto.getCategoria())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .descripcion(producto.getDescripcion())
                .build();
    }
}