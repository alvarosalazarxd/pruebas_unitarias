package com.resena.ms_resena;


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
}