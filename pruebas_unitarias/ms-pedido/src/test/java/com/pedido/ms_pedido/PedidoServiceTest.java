package com.pedido.ms_pedido;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pedido.ms_pedido.client.ProductoClient;
import com.pedido.ms_pedido.dto.PedidoRequestDTO;
import com.pedido.ms_pedido.dto.PedidoResponseDTO;
import com.pedido.ms_pedido.dto.ProductoDTO;
import com.pedido.ms_pedido.model.Pedido;
import com.pedido.ms_pedido.repository.PedidoRepository;
import com.pedido.ms_pedido.service.PedidoService; // Importamos el servicio correcto
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProductoClient productoClient;

    @InjectMocks
    private PedidoService pedidoService; // Ahora apunta a tu clase Service

    @Test
    public void testCrearPedidoCalculaTotalCorrectamente() {
        // Arrange
        PedidoRequestDTO requestDTO = new PedidoRequestDTO();
        requestDTO.setProductoId(1L);
        requestDTO.setCantidad(2);
        requestDTO.setCliente("Juan Perfumes");

        ProductoDTO prodMock = new ProductoDTO();
        prodMock.setPrecio(100.0);
        prodMock.setNombre("Perfume Dior"); // Necesario para el convertirDTO

        // Ajustado a tu método real: obtenerProducto(id)
        when(productoClient.obtenerProducto(1L)).thenReturn(prodMock);
        
        // El repositorio guarda y devuelve el objeto pedido
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(i -> {
            Pedido p = (Pedido) i.getArguments()[0];
            p.setId(1L); // Simulamos que la DB le asigna un ID
            p.setEstado("PENDIENTE");
            p.setFechaPedido(java.time.LocalDateTime.now());
            return p;
        });

        // Act
        PedidoResponseDTO resultado = pedidoService.crearPedido(requestDTO);

        // Assert
        assertEquals(200.0, resultado.getTotal()); // 2 * 100
        assertEquals("PENDIENTE", resultado.getEstado());
        assertEquals("Perfume Dior", resultado.getNombreProducto());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }
}