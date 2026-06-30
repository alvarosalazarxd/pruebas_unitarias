package com.example.ms_notificaciones.controller;

import com.example.ms_notificaciones.dto.NotificacionDTO;
import com.example.ms_notificaciones.service.NotificacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Notificaciones",
        description = "API para la gestión de notificaciones"
)
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notificaciones")

public class NotificacionController {

    private final NotificacionService service;

    @Operation(summary = "Crear una nueva notificación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificación creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<NotificacionDTO> crearNotificacion(
            @RequestBody NotificacionDTO dto) {

        log.info("POST /api/notificaciones");

        return new ResponseEntity<>(
                service.crearNotificacion(dto),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar una notificación por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación encontrada"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionDTO> buscarPorId(

            @Parameter(description = "ID de la notificación")
            @PathVariable Long id) {

        log.info("GET /api/notificaciones/{}", id);

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Listar todas las notificaciones")
    @ApiResponse(responseCode = "200", description = "Lista de notificaciones")
    @GetMapping
    public ResponseEntity<List<NotificacionDTO>> listarTodas() {

        log.info("GET /api/notificaciones");

        return ResponseEntity.ok(service.listarTodas());
    }

    @Operation(summary = "Buscar notificaciones por usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificaciones encontradas"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<NotificacionDTO>> buscarPorUsuario(

            @Parameter(description = "ID del usuario")
            @PathVariable Long usuarioId) {

        log.info("GET /api/notificaciones/usuario/{}", usuarioId);

        return ResponseEntity.ok(service.buscarPorUsuario(usuarioId));
    }

    @Operation(summary = "Buscar notificaciones por estado")
    @ApiResponse(responseCode = "200", description = "Notificaciones encontradas")
    @GetMapping("/estado/{leida}")
    public ResponseEntity<List<NotificacionDTO>> buscarPorEstado(

            @Parameter(description = "Estado de lectura (true/false)")
            @PathVariable Boolean leida) {

        log.info("GET /api/notificaciones/estado/{}", leida);

        return ResponseEntity.ok(service.buscarPorEstado(leida));
    }

    @Operation(summary = "Marcar una notificación como leída")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación actualizada"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @PutMapping("/{id}/leida")
    public ResponseEntity<NotificacionDTO> marcarComoLeida(

            @Parameter(description = "ID de la notificación")
            @PathVariable Long id) {

        log.info("PUT /api/notificaciones/{}/leida", id);

        return ResponseEntity.ok(service.marcarComoLeida(id));
    }

    @Operation(summary = "Eliminar una notificación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notificación eliminada"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(

            @Parameter(description = "ID de la notificación")
            @PathVariable Long id) {

        log.info("DELETE /api/notificaciones/{}", id);

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}