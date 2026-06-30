package com.envios.ms_envios;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.envios.ms_envios.dto.EnvioRequestDTO;
import com.envios.ms_envios.dto.EnvioResponseDTO;
import com.envios.ms_envios.model.Envio;
import com.envios.ms_envios.repository.EnvioRepository;
import com.envios.ms_envios.service.impl.EnvioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioServiceImpl envioService;

    @Test
    
    public void testObtenerEnvioPorId() {
        
        Long idFalso = 1L;
        Envio entidadFalsa = new Envio(); 
        entidadFalsa.setId(idFalso);

       
        when(envioRepository.findById(idFalso)).thenReturn(Optional.of(entidadFalsa));

      
        EnvioResponseDTO resultado = envioService.obtenerEnvioPorId(idFalso);

        assertNotNull(resultado);
        assertEquals(idFalso, resultado.getId());
        
      
        verify(envioRepository, times(1)).findById(idFalso);
    }
    @Test
    void testObtenerEnvioNoExiste() {

        when(envioRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {

            envioService.obtenerEnvioPorId(1L);

        });

    }
    @Test
    void testObtenerTodos() {

        Envio envio =
                EnvioDataFactory.crearEnvioAleatorio();

        when(envioRepository.findAll())
                .thenReturn(java.util.List.of(envio));

        assertEquals(
                1,
                envioService.obtenerTodos().size());

    }
    @Test
    void testBuscarPorEstado() {

        Envio envio =
                EnvioDataFactory.crearEnvioAleatorio();

        when(envioRepository.findByEstado("PREPARANDO"))
                .thenReturn(java.util.List.of(envio));

        assertEquals(
                1,
                envioService.buscarPorEstado("PREPARANDO").size());

    }
    @Test
    void testBuscarPorTransportista() {

        Envio envio =
                EnvioDataFactory.crearEnvioAleatorio();

        when(envioRepository.findByTransportistaIgnoreCase(
                envio.getTransportista()))
                .thenReturn(java.util.List.of(envio));

        assertEquals(
                1,
                envioService.buscarPorTransportista(
                        envio.getTransportista()).size());

    }
    @Test
    void testBuscarPorPedido() {

        Envio envio =
                EnvioDataFactory.crearEnvioAleatorio();

        when(envioRepository.findByPedidoId(
                envio.getPedidoId()))
                .thenReturn(java.util.List.of(envio));

        assertEquals(
                1,
                envioService.buscarPorPedido(
                        envio.getPedidoId()).size());

    }
    @Test
    void testBuscarPorCodigo() {

        Envio envio =
                EnvioDataFactory.crearEnvioAleatorio();

        when(envioRepository.findByCodigoSeguimiento(
                envio.getCodigoSeguimiento()))
                .thenReturn(Optional.of(envio));

        EnvioResponseDTO dto =
                envioService.buscarPorCodigoSeguimiento(
                        envio.getCodigoSeguimiento());

        assertNotNull(dto);

        assertEquals(
                envio.getCodigoSeguimiento(),
                dto.getCodigoSeguimiento());

    }
    @Test
    void testEliminarEnvio() {

        Envio envio =
                EnvioDataFactory.crearEnvioAleatorio();

        when(envioRepository.findById(envio.getId()))
                .thenReturn(Optional.of(envio));

        envioService.eliminarEnvio(envio.getId());

        verify(envioRepository)
                .delete(envio);

    }
    @Test
    void testEliminarNoExiste() {

        when(envioRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {

            envioService.eliminarEnvio(1L);

        });

    }
    @Test
    void testActualizarEstado() {

        Envio envio =
                EnvioDataFactory.crearEnvioAleatorio();

        when(envioRepository.findById(envio.getId()))
                .thenReturn(Optional.of(envio));

        when(envioRepository.save(any(Envio.class)))
                .thenAnswer(i -> i.getArgument(0));

        EnvioResponseDTO dto =
                envioService.actualizarEstado(
                        envio.getId(),
                        "EN CAMINO");

        assertEquals(
                "EN CAMINO",
                dto.getEstado());

    }
    @Test
    void testActualizarEnvio() {

        Envio envio =
                EnvioDataFactory.crearEnvioAleatorio();

        EnvioRequestDTO dto =
                new EnvioRequestDTO();

        dto.setPedidoId(20L);
        dto.setDireccion("Concepción");
        dto.setTransportista("Chilexpress");

        when(envioRepository.findById(envio.getId()))
                .thenReturn(Optional.of(envio));

        when(envioRepository.save(any(Envio.class)))
                .thenAnswer(i -> i.getArgument(0));

        EnvioResponseDTO resultado =
                envioService.actualizarEnvio(
                        envio.getId(),
                        dto);

        assertEquals(
                "Concepción",
                resultado.getDireccion());

    }
}