package com.producto.ms_producto.controller;


import com.producto.ms_producto.dto.ProductoRequestDTO;
import com.producto.ms_producto.dto.ProductoResponseDTO;
import com.producto.ms_producto.service.ProductoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")

@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponseDTO>
    crearProducto(
            @Valid
            @RequestBody
            ProductoRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService
                        .crearProducto(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>>
    obtenerTodos() {

        return ResponseEntity.ok(
                productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO>
    obtenerProductoPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productoService
                        .obtenerProductoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO>
    actualizarProducto(
            @PathVariable Long id,

            @Valid
            @RequestBody
            ProductoRequestDTO requestDTO) {

        return ResponseEntity.ok(
                productoService.actualizarProducto(
                        id,
                        requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminarProducto(
            @PathVariable Long id) {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>>
    buscarPorCategoria(
            @PathVariable String categoria) {

        return ResponseEntity.ok(
                productoService.buscarPorCategoria(
                        categoria));
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ProductoResponseDTO>>
    buscarPorMarca(
            @PathVariable String marca) {

        return ResponseEntity.ok(
                productoService.buscarPorMarca(
                        marca));
    }

    @GetMapping("/precio/{precio}")
    public ResponseEntity<List<ProductoResponseDTO>>
    buscarPorPrecio(
            @PathVariable Double precio) {

        return ResponseEntity.ok(
                productoService.buscarPorPrecio(
                        precio));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProductoResponseDTO>>
    buscarPorNombre(
            @PathVariable String nombre) {

        return ResponseEntity.ok(
                productoService.buscarPorNombre(
                        nombre));
    }
}
