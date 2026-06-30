package com.example.ms_carrito;

import com.example.ms_carrito.dto.CarritoRequestDTO;
import com.example.ms_carrito.dto.CarritoResponseDTO;
import com.example.ms_carrito.exception.ResourceNotFoundException;
import com.example.ms_carrito.model.Carrito;
import com.example.ms_carrito.repository.CarritoRepository;
import com.example.ms_carrito.service.impl.CarritoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class CarritoServiceImplTest {

    @Mock
    private CarritoRepository carritoRepository;

    @InjectMocks
    private CarritoServiceImpl carritoService;

    private Carrito carrito;
    private CarritoRequestDTO request;
    private Faker faker;

    @BeforeEach
    void setUp() {

        faker = new Faker();

        carrito = new Carrito(
                1L,
                100L,
                25000.0,
                3,
                LocalDateTime.now()
        );

        request = new CarritoRequestDTO();

        request.setUsuarioId(100L);

        request.setTotal(
                faker.number().randomDouble(
                        2,
                        10000,
                        300000
                )
        );

        request.setCantidadProductos(
                faker.number().numberBetween(1,10)
        );
    }

    @Test
    void crearCarrito_deberiaCrearCorrectamente() {

        when(carritoRepository.save(any(Carrito.class)))
                .thenReturn(carrito);

        CarritoResponseDTO resultado =
                carritoService.crearCarrito(request);

        assertNotNull(resultado);
        assertEquals(100L, resultado.getUsuarioId());

        verify(carritoRepository)
                .save(any(Carrito.class));
    }

    @Test
    void obtenerCarritoPorId_deberiaRetornarCarrito() {

        when(carritoRepository.findById(1L))
                .thenReturn(Optional.of(carrito));

        CarritoResponseDTO resultado =
                carritoService.obtenerCarritoPorId(1L);

        assertEquals(1L, resultado.getId());
        assertEquals(25000.0, resultado.getTotal());
    }

    @Test
    void obtenerCarritoPorId_deberiaLanzarExcepcion() {

        when(carritoRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> carritoService.obtenerCarritoPorId(1L)
        );
    }

    @Test
    void obtenerTodosLosCarritos_deberiaRetornarLista() {

        when(carritoRepository.findAll())
                .thenReturn(List.of(carrito));

        List<CarritoResponseDTO> resultado =
                carritoService.obtenerTodosLosCarritos();

        assertEquals(1, resultado.size());
    }

    @Test
    void actualizarCarrito_deberiaActualizar() {

        when(carritoRepository.findById(1L))
                .thenReturn(Optional.of(carrito));

        when(carritoRepository.save(any(Carrito.class)))
                .thenReturn(carrito);

        CarritoResponseDTO resultado =
                carritoService.actualizarCarrito(1L, request);

        assertEquals(100L, resultado.getUsuarioId());

        verify(carritoRepository)
                .save(any(Carrito.class));
    }

    @Test
    void eliminarCarrito_deberiaEliminar() {

        when(carritoRepository.existsById(1L))
                .thenReturn(true);

        carritoService.eliminarCarrito(1L);

        verify(carritoRepository)
                .deleteById(1L);
    }

    @Test
    void eliminarCarrito_deberiaLanzarExcepcion() {

        when(carritoRepository.existsById(1L))
                .thenReturn(false);

        assertThrows(
                ResourceNotFoundException.class,
                () -> carritoService.eliminarCarrito(1L)
        );
    }

}