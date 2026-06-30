package com.inventario.ms_inventario.controller;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.inventario.ms_inventario.dto.InventarioRequestDTO;
import com.inventario.ms_inventario.dto.InventarioResponseDTO;
import com.inventario.ms_inventario.service.InventarioService;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")

@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<InventarioResponseDTO>
    crearInventario(

            @Valid
            @RequestBody
            InventarioRequestDTO requestDTO) {

        return ResponseEntity.status(
                HttpStatus.CREATED)

                .body(
                        inventarioService
                                .crearInventario(requestDTO)
                );
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>>
    obtenerTodos() {

        return ResponseEntity.ok(
                inventarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO>
    obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                inventarioService.obtenerPorId(id));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<InventarioResponseDTO>
    buscarPorProducto(
            @PathVariable Long productoId) {

        return ResponseEntity.ok(
                inventarioService.buscarPorProducto(
                        productoId));
    }

    @GetMapping("/stock-bajo/{stock}")
    public ResponseEntity<List<InventarioResponseDTO>>
    stockBajo(
            @PathVariable Integer stock) {

        return ResponseEntity.ok(
                inventarioService.stockBajo(stock));
    }

    @GetMapping("/ubicacion/{ubicacion}")
    public ResponseEntity<List<InventarioResponseDTO>>
    buscarPorUbicacion(
            @PathVariable String ubicacion) {

        return ResponseEntity.ok(
                inventarioService.buscarPorUbicacion(
                        ubicacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO>
    actualizarInventario(
            @PathVariable Long id,

            @Valid
            @RequestBody
            InventarioRequestDTO requestDTO) {

        return ResponseEntity.ok(
                inventarioService.actualizarInventario(
                        id,
                        requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminarInventario(
            @PathVariable Long id) {

        inventarioService.eliminarInventario(id);

        return ResponseEntity.noContent().build();
    }
}