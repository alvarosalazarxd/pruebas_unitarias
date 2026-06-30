package com.resena.ms_resena.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resena.ms_resena.client.ProductoClient;
import com.resena.ms_resena.dto.ProductoDTO;
import com.resena.ms_resena.dto.ResenaRequestDTO;
import com.resena.ms_resena.dto.ResenaResponseDTO;
import com.resena.ms_resena.exception.ResourceNotFoundException;
import com.resena.ms_resena.model.Resena;
import com.resena.ms_resena.repository.ResenaRepository;
import com.resena.ms_resena.service.ResenaService;


import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class ResenaServiceImpl
        implements ResenaService {

    private final ResenaRepository resenaRepository;

    private final ProductoClient productoClient;

    @Override
    public ResenaResponseDTO crearResena(
            ResenaRequestDTO requestDTO) {

        ProductoDTO producto =
                productoClient.obtenerProducto(
                        requestDTO.getProductoId());

        Resena resena = new Resena();

        resena.setProductoId(
                requestDTO.getProductoId());

        resena.setUsuario(
                requestDTO.getUsuario());

        resena.setCalificacion(
                requestDTO.getCalificacion());

        resena.setComentario(
                requestDTO.getComentario());

        Resena guardada =
                resenaRepository.save(resena);

        return convertirDTO(
                guardada,
                producto.getNombre());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResenaResponseDTO>
    obtenerTodas() {

        return resenaRepository.findAll()
                .stream()
                .map(resena -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    resena.getProductoId());

                    return convertirDTO(
                            resena,
                            producto.getNombre());
                })
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResenaResponseDTO
    obtenerPorId(Long id) {

        Resena resena =
                resenaRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reseña no encontrada"));

        ProductoDTO producto =
                productoClient.obtenerProducto(
                        resena.getProductoId());

        return convertirDTO(
                resena,
                producto.getNombre());
    }
    @Override
public ResenaResponseDTO actualizarResena(
        Long id,
        ResenaRequestDTO requestDTO) {

    Resena resena =
            resenaRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Reseña no encontrada"));

    ProductoDTO producto =
            productoClient.obtenerProducto(
                    requestDTO.getProductoId());

    resena.setProductoId(
            requestDTO.getProductoId());

    resena.setUsuario(
            requestDTO.getUsuario());

    resena.setCalificacion(
            requestDTO.getCalificacion());

    resena.setComentario(
            requestDTO.getComentario());

    Resena actualizada =
            resenaRepository.save(resena);

    return convertirDTO(
            actualizada,
            producto.getNombre());
}
    @Override
    public void eliminarResena(Long id) {

        Resena resena =
                resenaRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reseña no encontrada"));

        resenaRepository.delete(resena);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResenaResponseDTO>
    buscarPorProducto(Long productoId) {

        return resenaRepository.findByProductoId(
                        productoId)
                .stream()
                .map(resena -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    resena.getProductoId());

                    return convertirDTO(
                            resena,
                            producto.getNombre());
                })
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResenaResponseDTO>
    buscarPorCalificacion(
            Integer calificacion) {

        return resenaRepository
                .findByCalificacion(calificacion)
                .stream()
                .map(resena -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    resena.getProductoId());

                    return convertirDTO(
                            resena,
                            producto.getNombre());
                })
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResenaResponseDTO>
    buscarPorUsuario(String usuario) {

        return resenaRepository
                .findByUsuarioContainingIgnoreCase(
                        usuario)
                .stream()
                .map(resena -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    resena.getProductoId());

                    return convertirDTO(
                            resena,
                            producto.getNombre());
                })
                .toList();
    }

    private ResenaResponseDTO convertirDTO(
            Resena resena,
            String nombreProducto) {

        ResenaResponseDTO dto =
                new ResenaResponseDTO();

        dto.setId(resena.getId());
        dto.setProductoId(
                resena.getProductoId());

        dto.setNombreProducto(
                nombreProducto);

        dto.setUsuario(
                resena.getUsuario());

        dto.setCalificacion(
                resena.getCalificacion());

        dto.setComentario(
                resena.getComentario());

        dto.setFechaResena(
                resena.getFechaResena());

        return dto;
    }
}