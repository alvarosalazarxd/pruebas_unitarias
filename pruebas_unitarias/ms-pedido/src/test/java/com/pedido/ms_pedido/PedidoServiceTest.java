package com.pedido.ms_pedido;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import com.pedido.ms_pedido.client.ProductoClient;
import com.pedido.ms_pedido.dto.PedidoRequestDTO;
import com.pedido.ms_pedido.dto.PedidoResponseDTO;
import com.pedido.ms_pedido.dto.ProductoDTO;
import com.pedido.ms_pedido.model.Pedido;
import com.pedido.ms_pedido.repository.PedidoRepository;
import com.pedido.ms_pedido.service.PedidoService; 
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
    @Test
    public void testEliminarPedido() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        doNothing().when(pedidoRepository).delete(pedido);

        pedidoService.eliminarPedido(id);

        verify(pedidoRepository, times(1)).delete(pedido);
    }
    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Pedido pedido = TestDataFactory.unPedido(1L, "Cliente X", 1, 100.0);
        pedido.setId(id);
        
        ProductoDTO prod = new ProductoDTO();
        prod.setNombre("Perfume");
        
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(productoClient.obtenerProducto(pedido.getProductoId())).thenReturn(prod);

        PedidoResponseDTO resultado = pedidoService.obtenerPorId(id);

        assertEquals("Perfume", resultado.getNombreProducto());
    }
    @Test
    public void testActualizarEstado() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setProductoId(1L);
        
        ProductoDTO prod = new ProductoDTO();
        prod.setNombre("Perfume");

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(i -> i.getArgument(0));
        when(productoClient.obtenerProducto(1L)).thenReturn(prod);

        PedidoResponseDTO resultado = pedidoService.actualizarEstado(id, "ENVIADO");

        assertEquals("ENVIADO", resultado.getEstado());
    }
    @Test
    public void testObtenerTodos() {
        
        Pedido pedido = TestDataFactory.unPedido(1L, "Juan", 1, 100.0);
        ProductoDTO prod = new ProductoDTO();
        prod.setNombre("Perfume");
        
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));
        when(productoClient.obtenerProducto(1L)).thenReturn(prod);

       
        var resultado = pedidoService.obtenerTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }
    @Test
    public void testBuscarPorCliente() {
        String nombre = "Juan";
        Pedido pedido = TestDataFactory.unPedido(1L, nombre, 1, 100.0);
        ProductoDTO prod = new ProductoDTO();
        prod.setNombre("Perfume");

        when(pedidoRepository.findByClienteContainingIgnoreCase(nombre)).thenReturn(List.of(pedido));
        when(productoClient.obtenerProducto(1L)).thenReturn(prod);

        var resultado = pedidoService.buscarPorCliente(nombre);

        assertEquals(1, resultado.size());
        assertEquals(nombre, resultado.get(0).getCliente());
    }
    @Test
    public void testBuscarPorEstado() {
        String estado = "PENDIENTE";
        Pedido pedido = TestDataFactory.unPedido(1L, "Juan", 1, 100.0);
        pedido.setEstado(estado);
        
        ProductoDTO prod = new ProductoDTO();
        prod.setNombre("Perfume");

        when(pedidoRepository.findByEstado(estado)).thenReturn(List.of(pedido));
        when(productoClient.obtenerProducto(1L)).thenReturn(prod);

        var resultado = pedidoService.buscarPorEstado(estado);

        assertFalse(resultado.isEmpty());
        assertEquals(estado, resultado.get(0).getEstado());
    }
    @Test
    public void testBuscarPorProducto() {
        Long productoId = 1L;
        Pedido pedido = TestDataFactory.unPedido(productoId, "Juan", 1, 100.0);
        
        ProductoDTO prod = new ProductoDTO();
        prod.setNombre("Perfume");

        when(pedidoRepository.findByProductoId(productoId)).thenReturn(List.of(pedido));
        when(productoClient.obtenerProducto(productoId)).thenReturn(prod);

        var resultado = pedidoService.buscarPorProducto(productoId);

        assertFalse(resultado.isEmpty());
        assertEquals(productoId, resultado.get(0).getProductoId());
    }
}