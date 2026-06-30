package com.resena.ms_resena.controller;



import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.resena.ms_resena.dto.ResenaRequestDTO;
import com.resena.ms_resena.dto.ResenaResponseDTO;
import com.resena.ms_resena.service.ResenaService;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")

@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    @PostMapping
    public ResponseEntity<ResenaResponseDTO>
    crearResena(

            @Valid
            @RequestBody
            ResenaRequestDTO requestDTO) {

        return ResponseEntity.status(
                HttpStatus.CREATED)

                .body(
                        resenaService
                                .crearResena(requestDTO)
                );
    }

    @GetMapping
    public ResponseEntity<List<ResenaResponseDTO>>
    obtenerTodas() {

        return ResponseEntity.ok(
                resenaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaResponseDTO>
    obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resenaService.obtenerPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResenaResponseDTO>
    actualizarResena(

                @PathVariable Long id,

                @Valid
                @RequestBody
                ResenaRequestDTO requestDTO) {

                return ResponseEntity.ok(
                        resenaService.actualizarResena(
                    id,
                    requestDTO));
                
        }       
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminarResena(
            @PathVariable Long id) {

        resenaService.eliminarResena(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ResenaResponseDTO>>
    buscarPorProducto(
            @PathVariable Long productoId) {

        return ResponseEntity.ok(
                resenaService.buscarPorProducto(
                        productoId));
    }

    @GetMapping("/calificacion/{calificacion}")
    public ResponseEntity<List<ResenaResponseDTO>>
    buscarPorCalificacion(
            @PathVariable Integer calificacion) {

        return ResponseEntity.ok(
                resenaService.buscarPorCalificacion(
                        calificacion));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<ResenaResponseDTO>>
    buscarPorUsuario(
            @PathVariable String usuario) {

        return ResponseEntity.ok(
                resenaService.buscarPorUsuario(
                        usuario));
    }
}
