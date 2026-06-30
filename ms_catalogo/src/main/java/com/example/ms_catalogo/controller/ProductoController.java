package com.example.ms_catalogo.controller;

import com.example.ms_catalogo.dto.ProductoRequestDTO;
import com.example.ms_catalogo.dto.ProductoResponseDTO;
import com.example.ms_catalogo.service.ProductoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDTO crearProducto(
            @RequestBody ProductoRequestDTO requestDTO) {

        return productoService.crearProducto(
                requestDTO);
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO obtenerProductoPorId(
            @PathVariable Long id) {

        return productoService.obtenerProductoPorId(id);
    }

    @GetMapping
    public List<ProductoResponseDTO> obtenerTodos() {

        return productoService.obtenerTodos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(
            @PathVariable Long id) {

        productoService.eliminarProducto(id);
    }
}