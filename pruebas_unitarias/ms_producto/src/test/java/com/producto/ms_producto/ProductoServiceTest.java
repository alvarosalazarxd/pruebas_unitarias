package com.producto.ms_producto;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.producto.ms_producto.dto.ProductoResponseDTO;
import com.producto.ms_producto.model.Producto; // Asegúrate que este sea tu paquete de modelo
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
}