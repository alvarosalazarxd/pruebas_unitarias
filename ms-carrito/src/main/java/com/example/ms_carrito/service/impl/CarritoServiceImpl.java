package com.example.ms_carrito.service.impl;

import com.example.ms_carrito.dto.CarritoRequestDTO;
import com.example.ms_carrito.dto.CarritoResponseDTO;
import com.example.ms_carrito.exception.ResourceNotFoundException;
import com.example.ms_carrito.model.Carrito;
import com.example.ms_carrito.repository.CarritoRepository;
import com.example.ms_carrito.service.CarritoService;

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

public class CarritoServiceImpl
        implements CarritoService {

    private final CarritoRepository carritoRepository;

    @Override
    public CarritoResponseDTO crearCarrito(
            CarritoRequestDTO requestDTO) {

        log.info("Creando nuevo carrito");

        validarDatosCarrito(requestDTO);

        Carrito carrito =
                convertirDTOAEntidad(requestDTO);

        Carrito carritoGuardado =
                carritoRepository.save(carrito);

        log.info("Carrito creado con ID: {}",
                carritoGuardado.getId());

        return convertirEntidadADTO(
                carritoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public CarritoResponseDTO obtenerCarritoPorId(
            Long id) {

        log.info("Buscando carrito con ID: {}",
                id);

        Carrito carrito =
                carritoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Carrito no encontrado con ID: "
                                                + id));

        return convertirEntidadADTO(carrito);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarritoResponseDTO>
    obtenerTodosLosCarritos() {

        log.info("Obteniendo todos los carritos");

        return carritoRepository.findAll()
                .stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarritoResponseDTO actualizarCarrito(
            Long id,
            CarritoRequestDTO requestDTO) {

        log.info("Actualizando carrito con ID: {}",
                id);

        Carrito carrito =
                carritoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Carrito no encontrado con ID: "
                                                + id));

        validarDatosCarrito(requestDTO);

        carrito.setUsuarioId(
                requestDTO.getUsuarioId());

        carrito.setTotal(
                requestDTO.getTotal());

        carrito.setCantidadProductos(
                requestDTO.getCantidadProductos());

        Carrito carritoActualizado =
                carritoRepository.save(carrito);

        log.info("Carrito actualizado: {}",
                id);

        return convertirEntidadADTO(
                carritoActualizado);
    }

    @Override
    public void eliminarCarrito(Long id) {

        log.info("Eliminando carrito con ID: {}",
                id);

        if (!carritoRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Carrito no encontrado con ID: "
                            + id);
        }

        carritoRepository.deleteById(id);

        log.info("Carrito eliminado: {}",
                id);
    }

    private void validarDatosCarrito(
            CarritoRequestDTO requestDTO) {

        if (requestDTO.getUsuarioId() == null) {

            throw new IllegalArgumentException(
                    "El usuario es obligatorio");
        }

        if (requestDTO.getTotal() == null
                || requestDTO.getTotal() < 0) {

            throw new IllegalArgumentException(
                    "El total no puede ser negativo");
        }

        if (requestDTO.getCantidadProductos() == null
                || requestDTO.getCantidadProductos() < 0) {

            throw new IllegalArgumentException(
                    "La cantidad de productos no puede ser negativa");
        }
    }

    private Carrito convertirDTOAEntidad(
            CarritoRequestDTO dto) {

        Carrito carrito = new Carrito();

        carrito.setUsuarioId(dto.getUsuarioId());
        carrito.setTotal(dto.getTotal());
        carrito.setCantidadProductos(
                dto.getCantidadProductos());

        return carrito;
    }

    private CarritoResponseDTO convertirEntidadADTO(
            Carrito carrito) {

        return CarritoResponseDTO.builder()
                .id(carrito.getId())
                .usuarioId(carrito.getUsuarioId())
                .total(carrito.getTotal())
                .cantidadProductos(
                        carrito.getCantidadProductos())
                .fechaCreacion(
                        carrito.getFechaCreacion())
                .build();
    }
}