package com.envios.ms_envios.service;



import java.util.List;

import com.envios.ms_envios.dto.EnvioRequestDTO;
import com.envios.ms_envios.dto.EnvioResponseDTO;

public interface EnvioService {

    // Crear envío
    EnvioResponseDTO crearEnvio(EnvioRequestDTO requestDTO);

    // Obtener envío por ID
    EnvioResponseDTO obtenerEnvioPorId(Long id);

    // Obtener todos los envíos
    List<EnvioResponseDTO> obtenerTodos();

    // Actualizar envío completo
    EnvioResponseDTO actualizarEnvio(
            Long id,
            EnvioRequestDTO requestDTO);

    // Actualizar solamente estado
    EnvioResponseDTO actualizarEstado(
            Long id,
            String estado);

    // Buscar por código seguimiento
    EnvioResponseDTO buscarPorCodigoSeguimiento(
            String codigoSeguimiento);

    // Buscar por estado
    List<EnvioResponseDTO> buscarPorEstado(
            String estado);

    // Buscar por transportista
    List<EnvioResponseDTO> buscarPorTransportista(
            String transportista);

    // Buscar por pedido
    List<EnvioResponseDTO> buscarPorPedido(
            Long pedidoId);

    // Eliminar envío
    void eliminarEnvio(Long id);
}