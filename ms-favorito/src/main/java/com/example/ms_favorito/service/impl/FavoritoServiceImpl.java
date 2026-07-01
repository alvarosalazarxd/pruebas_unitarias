package com.example.ms_favorito.service.impl;

import com.example.ms_favorito.client.ProductoClient;
import com.example.ms_favorito.dto.FavoritoDTO;
import com.example.ms_favorito.dto.ProductoDTO;
import com.example.ms_favorito.exception.ResourceNotFoundException;
import com.example.ms_favorito.model.Favorito;
import com.example.ms_favorito.repository.FavoritoRepository;
import com.example.ms_favorito.service.FavoritoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FavoritoServiceImpl
        implements FavoritoService {

    private final FavoritoRepository favoritoRepository;

    private final ProductoClient productoClient;

    @Override
    public FavoritoDTO guardarFavorito(
            FavoritoDTO favoritoDTO) {

        ProductoDTO producto =
                productoClient
                        .buscarProducto(
                                favoritoDTO.getProductoId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Producto no encontrado"));

        Favorito favorito = new Favorito();

        favorito.setUsuarioId(
                favoritoDTO.getUsuarioId());

        favorito.setProductoId(
                producto.getId());

        favorito.setNombreProducto(
                producto.getNombre());

        Favorito guardado =
                favoritoRepository.save(favorito);

        return convertirDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public FavoritoDTO buscarPorId(Long id) {

        Favorito favorito =
                favoritoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Favorito no encontrado"));

        return convertirDTO(favorito);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FavoritoDTO>
    listarFavoritos() {

        return favoritoRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }
    @Override
public FavoritoDTO actualizarFavorito(
        Long id,
        FavoritoDTO favoritoDTO) {

    Favorito favorito =
            favoritoRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Favorito no encontrado"));

    ProductoDTO producto =
            productoClient
                    .buscarProducto(favoritoDTO.getProductoId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Producto no encontrado"));

    favorito.setUsuarioId(
            favoritoDTO.getUsuarioId());

    favorito.setProductoId(
            producto.getId());

    favorito.setNombreProducto(
            producto.getNombre());

    Favorito actualizado =
            favoritoRepository.save(favorito);

    return convertirDTO(actualizado);
}
    @Override
    public void eliminarFavorito(Long id) {

        Favorito favorito =
                favoritoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Favorito no encontrado"));

        favoritoRepository.delete(favorito);
    }

    private FavoritoDTO convertirDTO(
            Favorito favorito) {

        FavoritoDTO dto =
                new FavoritoDTO();

        dto.setId(favorito.getId());

        dto.setUsuarioId(
                favorito.getUsuarioId());

        dto.setProductoId(
                favorito.getProductoId());

        dto.setNombreProducto(
                favorito.getNombreProducto());

        dto.setFechaAgregado(
                favorito.getFechaAgregado());

        return dto;
    }
}