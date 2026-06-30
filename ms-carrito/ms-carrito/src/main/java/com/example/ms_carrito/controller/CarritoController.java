package com.example.ms_carrito.controller;

import com.example.ms_carrito.dto.CarritoRequestDTO;
import com.example.ms_carrito.dto.CarritoResponseDTO;
import com.example.ms_carrito.service.CarritoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
        name = "Carritos",
        description = "Microservicio encargado de la administración de los carritos de compra de Perfumelandia."
)
@RestController
@RequestMapping("/api/carritos")
@RequiredArgsConstructor
@Slf4j

public class CarritoController {

    private final CarritoService carritoService;

    @Operation(
            summary = "Crear un carrito",
            description = "Crea un nuevo carrito de compras a partir de la información enviada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrito creado correctamente"),
            @ApiResponse(responseCode = "400", description = "La información enviada es inválida")
    })
    @PostMapping
    public ResponseEntity<CarritoResponseDTO> crearCarrito(

            @RequestBody(
                    description = "Información necesaria para crear un carrito",
                    required = true
            )
            @org.springframework.web.bind.annotation.RequestBody
            CarritoRequestDTO requestDTO) {

        log.info("POST /api/carritos - Crear carrito");

        CarritoResponseDTO carritoCreado = carritoService.crearCarrito(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(carritoCreado);
    }

    @Operation(
            summary = "Buscar carrito por ID",
            description = "Obtiene un carrito específico utilizando su identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un carrito con ese ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarritoResponseDTO> obtenerCarritoPorId(

            @Parameter(
                    description = "Identificador único del carrito",
                    example = "1"
            )
            @PathVariable Long id) {

        log.info("GET /api/carritos/{} - Obtener carrito", id);

        return ResponseEntity.ok(carritoService.obtenerCarritoPorId(id));
    }

    @Operation(
            summary = "Listar todos los carritos",
            description = "Obtiene la lista completa de carritos registrados."
    )
    @ApiResponse(responseCode = "200", description = "Lista de carritos obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<CarritoResponseDTO>> obtenerTodosLosCarritos() {

        log.info("GET /api/carritos - Obtener todos los carritos");

        return ResponseEntity.ok(carritoService.obtenerTodosLosCarritos());
    }

    @Operation(
            summary = "Actualizar un carrito",
            description = "Actualiza la información de un carrito existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarritoResponseDTO> actualizarCarrito(

            @Parameter(
                    description = "Identificador del carrito",
                    example = "1"
            )
            @PathVariable Long id,

            @RequestBody(
                    description = "Nueva información del carrito",
                    required = true
            )
            @org.springframework.web.bind.annotation.RequestBody
            CarritoRequestDTO requestDTO) {

        log.info("PUT /api/carritos/{} - Actualizar carrito", id);

        return ResponseEntity.ok(carritoService.actualizarCarrito(id, requestDTO));
    }

    @Operation(
            summary = "Eliminar un carrito",
            description = "Elimina un carrito mediante su identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carrito eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(

            @Parameter(
                    description = "Identificador del carrito",
                    example = "1"
            )
            @PathVariable Long id) {

        log.info("DELETE /api/carritos/{} - Eliminar carrito", id);

        carritoService.eliminarCarrito(id);

        return ResponseEntity.noContent().build();
    }
}