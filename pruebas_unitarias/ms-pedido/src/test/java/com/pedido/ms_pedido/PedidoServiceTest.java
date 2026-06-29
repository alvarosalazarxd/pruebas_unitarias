package com.pedido.ms_pedido;

import static org.mockito.ArgumentMatchers.any;
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
    private PedidoService pedidoService; 

   @Test
    public void testCrearPedidoGuardaSoloUnaVez() {

        PedidoRequestDTO requestDTO = new PedidoRequestDTO();
        requestDTO.setProductoId(1L);
        requestDTO.setCantidad(2);
        requestDTO.setCliente("Juan");

        ProductoDTO producto = new ProductoDTO();
        producto.setPrecio(100.0);
        producto.setNombre("Perfume Dior");

        when(productoClient.obtenerProducto(1L)).thenReturn(producto);
        when(pedidoRepository.save(any(Pedido.class)))
                .thenAnswer(i -> i.getArgument(0));

        pedidoService.crearPedido(requestDTO);

        verify(pedidoRepository, times(1))
                .save(any(Pedido.class));
    }
    @Test
    public void testCrearPedidoGuardaClienteCorrectamente() {

        PedidoRequestDTO requestDTO = new PedidoRequestDTO();
        requestDTO.setProductoId(1L);
        requestDTO.setCantidad(3);
        requestDTO.setCliente("Alvaro");

        ProductoDTO producto = new ProductoDTO();
        producto.setPrecio(50.0);
        producto.setNombre("Invictus");

        when(productoClient.obtenerProducto(1L)).thenReturn(producto);
        when(pedidoRepository.save(any(Pedido.class)))
                .thenAnswer(i -> i.getArgument(0));

        PedidoResponseDTO resultado =
                pedidoService.crearPedido(requestDTO);

        assertEquals("Alvaro", resultado.getCliente());
    }
    @Test
    public void testCrearPedidoCantidadCorrecta() {

        PedidoRequestDTO requestDTO = new PedidoRequestDTO();
        requestDTO.setProductoId(1L);
        requestDTO.setCantidad(5);

        ProductoDTO producto = new ProductoDTO();
        producto.setPrecio(20.0);
        producto.setNombre("Versace");

        when(productoClient.obtenerProducto(1L)).thenReturn(producto);
        when(pedidoRepository.save(any(Pedido.class)))
                .thenAnswer(i -> i.getArgument(0));

        PedidoResponseDTO resultado =
                pedidoService.crearPedido(requestDTO);

        assertEquals(5, resultado.getCantidad());
    }
    @Test
    public void testCrearPedidoConOtroPrecio() {

        PedidoRequestDTO requestDTO = new PedidoRequestDTO();
        requestDTO.setProductoId(1L);
        requestDTO.setCantidad(4);

        ProductoDTO producto = new ProductoDTO();
        producto.setPrecio(80.0);
        producto.setNombre("Bleu");

        when(productoClient.obtenerProducto(1L)).thenReturn(producto);
        when(pedidoRepository.save(any(Pedido.class)))
                .thenAnswer(i -> i.getArgument(0));

        PedidoResponseDTO resultado =
                pedidoService.crearPedido(requestDTO);

        assertEquals(320.0, resultado.getTotal());
    }
    @Test
    public void testNombreProductoCorrecto() {

        PedidoRequestDTO requestDTO = new PedidoRequestDTO();
        requestDTO.setProductoId(1L);
        requestDTO.setCantidad(1);

        ProductoDTO producto = new ProductoDTO();
        producto.setPrecio(90.0);
        producto.setNombre("Acqua Di Gio");

        when(productoClient.obtenerProducto(1L)).thenReturn(producto);
        when(pedidoRepository.save(any(Pedido.class)))
                .thenAnswer(i -> i.getArgument(0));

        PedidoResponseDTO resultado =
                pedidoService.crearPedido(requestDTO);

        assertEquals("Acqua Di Gio",
                resultado.getNombreProducto());
    }
}