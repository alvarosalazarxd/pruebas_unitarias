package com.producto.ms_producto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.producto.ms_producto.dto.ProductoRequestDTO;
import com.producto.ms_producto.dto.ProductoResponseDTO;
import com.producto.ms_producto.model.Producto; 
import com.producto.ms_producto.repository.ProductoRepository;
import com.producto.ms_producto.service.impl.ProductoServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoServiceImpl service;

    @Test
    void testObtenerProductoPorId() {
        
        Producto productoFalso = TestDataFactory.crearProductoAleatorio();
        when(repository.findById(anyLong())).thenReturn(Optional.of(productoFalso));

        
        ProductoResponseDTO resultado = service.obtenerProductoPorId(productoFalso.getId());

        
        assertNotNull(resultado);
        assertEquals(productoFalso.getNombre(), resultado.getNombre());
    }
    @Test
    void testObtenerProductoNoExiste() {

        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {

            service.obtenerProductoPorId(1L);

        });
    }
    @Test
    void testObtenerTodos() {

        Producto producto =
                TestDataFactory.crearProductoAleatorio();

        when(repository.findAll())
                .thenReturn(java.util.List.of(producto));

        assertEquals(
                1,
                service.obtenerTodos().size());
    }
    @Test
    void testBuscarCategoria() {

        Producto producto =
                TestDataFactory.crearProductoAleatorio();

        when(repository.findByCategoria("Perfumes"))
                .thenReturn(java.util.List.of(producto));

        assertEquals(
                1,
                service.buscarPorCategoria("Perfumes").size());
    }
    @Test
    void testBuscarMarca() {

        Producto producto =
                TestDataFactory.crearProductoAleatorio();

        when(repository.findByMarca(producto.getMarca()))
                .thenReturn(java.util.List.of(producto));

        assertEquals(
                1,
                service.buscarPorMarca(producto.getMarca()).size());
    }
    @Test
    void testBuscarPrecio() {

        Producto producto =
                TestDataFactory.crearProductoAleatorio();

        when(repository.findByPrecioLessThanEqual(1000.0))
                .thenReturn(java.util.List.of(producto));

        assertEquals(
                1,
                service.buscarPorPrecio(1000.0).size());
    }
    @Test
    void testBuscarNombre() {

        Producto producto =
                TestDataFactory.crearProductoAleatorio();

        when(repository.findByNombreContainingIgnoreCase(
                producto.getNombre()))
                .thenReturn(java.util.List.of(producto));

        assertEquals(
                1,
                service.buscarPorNombre(
                        producto.getNombre()).size());
    }
    @Test
    void testEliminarProducto() {

        Producto producto =
                TestDataFactory.crearProductoAleatorio();

        when(repository.findById(producto.getId()))
                .thenReturn(Optional.of(producto));

        service.eliminarProducto(producto.getId());

        verify(repository)
                .delete(producto);
    }
    @Test
    void testEliminarProductoNoExiste() {

        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {

            service.eliminarProducto(1L);

        });
    }
    @Test
    void testActualizarProducto() {

        Producto producto =
                TestDataFactory.crearProductoAleatorio();

        ProductoRequestDTO dto =
                new ProductoRequestDTO();

        dto.setNombre("Dior");
        dto.setMarca("Dior");
        dto.setCategoria("Perfumes");
        dto.setPrecio(250.0);
        dto.setStock(20);
        dto.setDescripcion("Perfume");

        when(repository.findById(producto.getId()))
                .thenReturn(Optional.of(producto));

        when(repository.save(any(Producto.class)))
                .thenAnswer(i -> i.getArgument(0));

        ProductoResponseDTO resultado =
                service.actualizarProducto(
                        producto.getId(),
                        dto);

        assertEquals(
                "Dior",
                resultado.getNombre());
    }
}