package com.envios.ms_envios.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.envios.ms_envios.dto.EnvioRequestDTO;
import com.envios.ms_envios.dto.EnvioResponseDTO;
import com.envios.ms_envios.model.Envio;
import com.envios.ms_envios.repository.EnvioRepository;
import com.envios.ms_envios.service.EnvioService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.envios.ms_envios.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EnvioServiceImpl implements EnvioService {

    private final EnvioRepository envioRepository;

    @Override
    public EnvioResponseDTO crearEnvio(
            EnvioRequestDTO requestDTO) {

        Envio envio = new Envio();

        envio.setPedidoId(requestDTO.getPedidoId());
        envio.setDireccion(requestDTO.getDireccion());
        envio.setTransportista(requestDTO.getTransportista());

        Envio guardado = envioRepository.save(envio);

        return convertirDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public EnvioResponseDTO obtenerEnvioPorId(Long id) {

        Envio envio = envioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Envío no encontrado"));

        return convertirDTO(envio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioResponseDTO> obtenerTodos() {

        return envioRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EnvioResponseDTO buscarPorCodigoSeguimiento(
            String codigoSeguimiento) {

        Envio envio = envioRepository
                .findByCodigoSeguimiento(codigoSeguimiento)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Envío no encontrado"));

        return convertirDTO(envio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioResponseDTO> buscarPorEstado(
            String estado) {

        return envioRepository.findByEstado(estado)
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioResponseDTO> buscarPorTransportista(
            String transportista) {

        return envioRepository
                .findByTransportistaIgnoreCase(transportista)
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioResponseDTO> buscarPorPedido(
            Long pedidoId) {

        return envioRepository.findByPedidoId(pedidoId)
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnvioResponseDTO actualizarEnvio(
            Long id,
            EnvioRequestDTO requestDTO) {

        Envio envio = envioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Envío no encontrado"));

        envio.setPedidoId(requestDTO.getPedidoId());
        envio.setDireccion(requestDTO.getDireccion());
        envio.setTransportista(requestDTO.getTransportista());

        Envio actualizado = envioRepository.save(envio);

        return convertirDTO(actualizado);
    }

    @Override
    public EnvioResponseDTO actualizarEstado(
            Long id,
            String estado) {

        Envio envio = envioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Envío no encontrado"));

        envio.setEstado(estado);

        Envio actualizado = envioRepository.save(envio);

        return convertirDTO(actualizado);
    }

    @Override
    public void eliminarEnvio(Long id) {

        Envio envio = envioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Envío no encontrado"));

        envioRepository.delete(envio);
    }

    private EnvioResponseDTO convertirDTO(Envio envio) {

        return EnvioResponseDTO.builder()
                .id(envio.getId())
                .pedidoId(envio.getPedidoId())
                .direccion(envio.getDireccion())
                .estado(envio.getEstado())
                .codigoSeguimiento(envio.getCodigoSeguimiento())
                .transportista(envio.getTransportista())
                .fechaEnvio(envio.getFechaEnvio())
                .build();
    }
}