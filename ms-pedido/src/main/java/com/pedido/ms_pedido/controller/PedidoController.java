package com.pedido.ms_pedido.controller;



import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.pedido.ms_pedido.dto.PedidoRequestDTO;
import com.pedido.ms_pedido.dto.PedidoResponseDTO;
import com.pedido.ms_pedido.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")

@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO>
    crearPedido(

            @Valid
            @RequestBody
            PedidoRequestDTO requestDTO) {

        return ResponseEntity.status(
                HttpStatus.CREATED)

                .body(
                        pedidoService
                                .crearPedido(requestDTO)
                );
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>>
    obtenerTodos() {

        return ResponseEntity.ok(
                pedidoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO>
    obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pedidoService.obtenerPorId(id));
    }

    @PutMapping("/{id}/{estado}")
    public ResponseEntity<PedidoResponseDTO>
    actualizarEstado(
            @PathVariable Long id,

            @PathVariable String estado) {

        return ResponseEntity.ok(
                pedidoService.actualizarEstado(
                        id,
                        estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminarPedido(
            @PathVariable Long id) {

        pedidoService.eliminarPedido(id);

        return ResponseEntity.noContent().build();
    }
}
