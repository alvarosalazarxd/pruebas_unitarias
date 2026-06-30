package com.envios.ms_envios.controller;

import com.envios.ms_envios.dto.EnvioRequestDTO;
import com.envios.ms_envios.dto.EnvioResponseDTO;
import com.envios.ms_envios.service.EnvioService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;

   
    @GetMapping
    public ResponseEntity<List<EnvioResponseDTO>>
    listarEnvios() {

        return ResponseEntity.ok(
                envioService.obtenerTodos());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO>
    obtenerPorId(@PathVariable Long id) {

        return ResponseEntity.ok(
                envioService.obtenerEnvioPorId(id));
    }

    @PostMapping
    public ResponseEntity<EnvioResponseDTO>
    crearEnvio(
            @Valid
            @RequestBody
            EnvioRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(envioService.crearEnvio(requestDTO));
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO>
    actualizarEnvio(
            @PathVariable Long id,
            @Valid
            @RequestBody
            EnvioRequestDTO requestDTO) {

        return ResponseEntity.ok(
                envioService.actualizarEnvio(
                        id,
                        requestDTO));
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminarEnvio(@PathVariable Long id) {

        envioService.eliminarEnvio(id);

        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<EnvioResponseDTO>
    buscarPorCodigo(
            @PathVariable String codigo) {

        return ResponseEntity.ok(
                envioService
                        .buscarPorCodigoSeguimiento(
                                codigo));
    }

    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<EnvioResponseDTO>>
    buscarPorEstado(
            @PathVariable String estado) {

        return ResponseEntity.ok(
                envioService.buscarPorEstado(
                        estado));
    }

    
    @GetMapping("/transportista/{transportista}")
    public ResponseEntity<List<EnvioResponseDTO>>
    buscarPorTransportista(
            @PathVariable String transportista) {

        return ResponseEntity.ok(
                envioService
                        .buscarPorTransportista(
                                transportista));
    }

    
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<EnvioResponseDTO>>
    buscarPorPedido(
            @PathVariable Long pedidoId) {

        return ResponseEntity.ok(
                envioService.buscarPorPedido(
                        pedidoId));
    }
}
