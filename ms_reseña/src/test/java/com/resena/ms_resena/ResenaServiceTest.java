package com.resena.ms_resena;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.resena.ms_resena.client.ProductoClient;
import com.resena.ms_resena.dto.ProductoDTO;
import com.resena.ms_resena.dto.ResenaRequestDTO;
import com.resena.ms_resena.dto.ResenaResponseDTO;
import com.resena.ms_resena.model.Resena;
import com.resena.ms_resena.repository.ResenaRepository;
import com.resena.ms_resena.service.impl.ResenaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private ProductoClient productoClient; 

    @InjectMocks
    private ResenaServiceImpl resenaService;

    @Test
    public void testCrearResena() {
        
        ResenaRequestDTO requestDTO = new ResenaRequestDTO();
        requestDTO.setProductoId(1L);
        requestDTO.setUsuario("usuarioTest");
        requestDTO.setCalificacion(5);
        requestDTO.setComentario("Excelente");

        ProductoDTO productoFalso = new ProductoDTO();
        productoFalso.setNombre("Producto Prueba");

        
        when(productoClient.obtenerProducto(1L)).thenReturn(productoFalso);
        
        Resena resenaGuardada = new Resena();
        resenaGuardada.setId(1L);
        resenaGuardada.setProductoId(1L);
        
        when(resenaRepository.save(any(Resena.class))).thenReturn(resenaGuardada);

       
        ResenaResponseDTO resultado = resenaService.crearResena(requestDTO);

        
        assertNotNull(resultado);
        assertEquals("Producto Prueba", resultado.getNombreProducto());
        verify(productoClient, times(1)).obtenerProducto(1L);
        verify(resenaRepository, times(1)).save(any(Resena.class));
    }
    @Test
    public void testObtenerPorId() {

        Resena resena = ResenaDataFactory.crearResenaAleatoria();
        resena.setId(1L);
        resena.setProductoId(1L);

        ProductoDTO producto = new ProductoDTO();
        producto.setNombre("Producto Prueba");

        when(resenaRepository.findById(1L))
                .thenReturn(java.util.Optional.of(resena));

        when(productoClient.obtenerProducto(1L))
                .thenReturn(producto);

        ResenaResponseDTO resultado =
                resenaService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Producto Prueba",
                resultado.getNombreProducto());
    }
    @Test
    public void testObtenerPorIdNoExiste() {

        when(resenaRepository.findById(1L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> {

            resenaService.obtenerPorId(1L);

        });
    }
    @Test
    public void testObtenerTodas() {

        Resena resena =
                ResenaDataFactory.crearResenaAleatoria();

        resena.setProductoId(1L);

        ProductoDTO producto =
                new ProductoDTO();

        producto.setNombre("Producto");

        when(resenaRepository.findAll())
                .thenReturn(java.util.List.of(resena));

        when(productoClient.obtenerProducto(1L))
                .thenReturn(producto);

        assertEquals(
                1,
                resenaService.obtenerTodas().size());
    }
    @Test
    public void testBuscarPorProducto() {

        Resena resena =
                ResenaDataFactory.crearResenaAleatoria();

        resena.setProductoId(5L);

        ProductoDTO producto =
                new ProductoDTO();

        producto.setNombre("Perfume");

        when(resenaRepository.findByProductoId(5L))
                .thenReturn(java.util.List.of(resena));

        when(productoClient.obtenerProducto(5L))
                .thenReturn(producto);

        assertEquals(
                1,
                resenaService.buscarPorProducto(5L).size());
    }
    @Test
    public void testBuscarPorCalificacion() {

        Resena resena =
                ResenaDataFactory.crearResenaAleatoria();

        resena.setProductoId(1L);
        resena.setCalificacion(5);

        ProductoDTO producto =
                new ProductoDTO();

        producto.setNombre("Perfume");

        when(resenaRepository.findByCalificacion(5))
                .thenReturn(java.util.List.of(resena));

        when(productoClient.obtenerProducto(1L))
                .thenReturn(producto);

        assertEquals(
                1,
                resenaService.buscarPorCalificacion(5).size());
    }
    @Test
    public void testBuscarPorUsuario() {

        Resena resena =
                ResenaDataFactory.crearResenaAleatoria();

        resena.setProductoId(1L);
        resena.setUsuario("alvaro");

        ProductoDTO producto =
                new ProductoDTO();

        producto.setNombre("Perfume");

        when(resenaRepository
                .findByUsuarioContainingIgnoreCase("alvaro"))
                .thenReturn(java.util.List.of(resena));

        when(productoClient.obtenerProducto(1L))
                .thenReturn(producto);

        assertEquals(
                1,
                resenaService.buscarPorUsuario("alvaro").size());
    }
    @Test
    public void testEliminarResena() {

        Resena resena =
                ResenaDataFactory.crearResenaAleatoria();

        when(resenaRepository.findById(1L))
                .thenReturn(java.util.Optional.of(resena));

        resenaService.eliminarResena(1L);

        verify(resenaRepository)
                .delete(resena);
    }
    @Test
    public void testEliminarResenaNoExiste() {

        when(resenaRepository.findById(1L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> {

            resenaService.eliminarResena(1L);

        });
    }
}