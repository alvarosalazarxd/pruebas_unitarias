package com.perfumes.perfumelandia.ms_usuarios.controller;

import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioRequestDTO;
import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioResponseDTO;
import com.perfumes.perfumelandia.ms_usuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor

public class UsuarioController {
     private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO>
    crearUsuario(
            @RequestBody UsuarioRequestDTO requestDTO) {

        return new ResponseEntity<>(
                usuarioService.crearUsuario(requestDTO),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>>
    obtenerTodos() {

        return ResponseEntity.ok(
                usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO>
    obtenerUsuario(@PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.obtenerUsuarioPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO>
    actualizarUsuario(

        @PathVariable Long id,

        @RequestBody UsuarioRequestDTO requestDTO) {

        return ResponseEntity.ok(
                usuarioService.actualizarUsuario(
                        id,
                        requestDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminarUsuario(@PathVariable Long id) {

        usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }

}
