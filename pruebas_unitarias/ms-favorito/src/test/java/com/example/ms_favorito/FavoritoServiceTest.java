package com.example.ms_favorito;

import static org.mockito.ArgumentMatchers.any;
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

import java.util.List;
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
    @Test
    public void testBuscarPorId() {

        Favorito favorito =
                TestDataFactory.unFavorito(
                        1L,
                        "Perfume Dior",
                        10L);

        when(favoritoRepository.findById(1L))
                .thenReturn(Optional.of(favorito));

        FavoritoDTO resultado =
                favoritoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getProductoId());
    }
    @Test
    public void testBuscarPorIdNoExiste() {

        when(favoritoRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {

            favoritoService.buscarPorId(1L);

        });
    }
    @Test
    public void testListarFavoritos() {

        Favorito favorito =
                TestDataFactory.unFavorito(
                        1L,
                        "Perfume Dior",
                        10L);

        when(favoritoRepository.findAll())
                .thenReturn(List.of(favorito));

        assertEquals(
                1,
                favoritoService.listarFavoritos().size());
    }
    @Test
    public void testEliminarFavorito() {

        Favorito favorito =
                TestDataFactory.unFavorito(
                        1L,
                        "Perfume Dior",
                        10L);

        when(favoritoRepository.findById(1L))
                .thenReturn(Optional.of(favorito));

        favoritoService.eliminarFavorito(1L);

        verify(favoritoRepository)
                .delete(favorito);
    }
    @Test
    public void testEliminarFavoritoNoExiste() {

        when(favoritoRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {

            favoritoService.eliminarFavorito(1L);

        });
    }
    @Test
    public void testActualizarFavorito() {

        Favorito favorito =
                TestDataFactory.unFavorito(
                        1L,
                        "Perfume Viejo",
                        10L);

        FavoritoDTO dto = new FavoritoDTO();

        dto.setUsuarioId(1L);
        dto.setProductoId(20L);

        ProductoDTO producto = new ProductoDTO();

        producto.setId(20L);
        producto.setNombre("Perfume Nuevo");

        when(favoritoRepository.findById(1L))
                .thenReturn(Optional.of(favorito));

        when(productoClient.buscarProducto(20L))
                .thenReturn(Optional.of(producto));

        when(favoritoRepository.save(any(Favorito.class)))
                .thenAnswer(i -> i.getArgument(0));

        FavoritoDTO resultado =
                favoritoService.actualizarFavorito(
                        1L,
                        dto);

        assertEquals(
                "Perfume Nuevo",
                resultado.getNombreProducto());
    }
    @Test
    public void testActualizarFavoritoProductoNoExiste() {

        Favorito favorito =
                TestDataFactory.unFavorito(
                        1L,
                        "Perfume",
                        10L);

        FavoritoDTO dto =
                new FavoritoDTO();

        dto.setUsuarioId(1L);
        dto.setProductoId(99L);

        when(favoritoRepository.findById(1L))
                .thenReturn(Optional.of(favorito));

        when(productoClient.buscarProducto(99L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {

            favoritoService.actualizarFavorito(
                    1L,
                    dto);

        });
    }
    @Test
    public void testGuardarLlamaSave() {

        FavoritoDTO dto =
                new FavoritoDTO();

        dto.setUsuarioId(1L);
        dto.setProductoId(10L);

        ProductoDTO producto =
                new ProductoDTO();

        producto.setId(10L);
        producto.setNombre("Perfume Dior");

        when(productoClient.buscarProducto(10L))
                .thenReturn(Optional.of(producto));

        when(favoritoRepository.save(any(Favorito.class)))
                .thenAnswer(i -> i.getArgument(0));

        favoritoService.guardarFavorito(dto);

        verify(favoritoRepository,
                times(1))
                .save(any(Favorito.class));
    }
}