package com.example.ms_favorito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.ms_favorito.client.ProductoClient;
import com.example.ms_favorito.dto.FavoritoDTO;
import com.example.ms_favorito.dto.ProductoDTO;
import com.example.ms_favorito.model.Favorito;
import com.example.ms_favorito.repository.FavoritoRepository;
import com.example.ms_favorito.service.impl.FavoritoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FavoritoServiceTest {

    @Mock
    private FavoritoRepository favoritoRepository;

    @Mock
    private ProductoClient productoClient; 

    @InjectMocks
    private FavoritoServiceImpl favoritoService;

    @Test
    public void testGuardarFavorito() {
        // Arrange
        FavoritoDTO dtoEntrada = new FavoritoDTO();
        dtoEntrada.setUsuarioId(1L);
        dtoEntrada.setProductoId(10L);

        ProductoDTO productoMock = new ProductoDTO();
        productoMock.setId(10L);
        productoMock.setNombre("Perfume Dior");

        when(productoClient.buscarProducto(10L)).thenReturn(Optional.of(productoMock));
        
        // Simulamos el guardado
        Favorito favoritoGuardado = new Favorito();
        favoritoGuardado.setId(1L);
        favoritoGuardado.setNombreProducto("Perfume Dior");
        
        when(favoritoRepository.save(any(Favorito.class))).thenReturn(favoritoGuardado);

        // Act
        FavoritoDTO resultado = favoritoService.guardarFavorito(dtoEntrada);

        // Assert
        assertNotNull(resultado);
        assertEquals("Perfume Dior", resultado.getNombreProducto());
        verify(productoClient, times(1)).buscarProducto(10L);
        verify(favoritoRepository, times(1)).save(any(Favorito.class));
    }
}