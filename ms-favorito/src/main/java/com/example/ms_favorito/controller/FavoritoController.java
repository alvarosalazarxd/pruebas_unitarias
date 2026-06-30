package com.example.ms_favorito.controller;

import com.example.ms_favorito.dto.FavoritoDTO;
import com.example.ms_favorito.service.FavoritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    @PostMapping
    public ResponseEntity<FavoritoDTO>
    guardarFavorito(
            @RequestBody
            FavoritoDTO favoritoDTO) {

        log.info(
                "POST /api/favoritos");

        FavoritoDTO respuesta =
                favoritoService
                        .guardarFavorito(
                                favoritoDTO);

        return new ResponseEntity<>(
                respuesta,
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoritoDTO>
    buscarFavorito(
            @PathVariable Long id) {

        log.info(
                "GET /api/favoritos/{}",
                id);

        return ResponseEntity.ok(
                favoritoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<FavoritoDTO>>
    listarFavoritos() {

        log.info(
                "GET /api/favoritos");

        return ResponseEntity.ok(
                favoritoService
                        .listarFavoritos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminarFavorito(
            @PathVariable Long id) {

        log.info(
                "DELETE /api/favoritos/{}",
                id);

        favoritoService.eliminarFavorito(id);

        return ResponseEntity
                .noContent()
                .build();
    }
    @PutMapping("/{id}")
        public ResponseEntity<FavoritoDTO> actualizarFavorito(
                @PathVariable Long id,
                @Valid @RequestBody FavoritoDTO favoritoDTO) {

        return ResponseEntity.ok(
                favoritoService.actualizarFavorito(id, favoritoDTO));
        }
}