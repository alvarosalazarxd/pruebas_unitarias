package com.pedido.ms_pedido.service;



import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedido.ms_pedido.client.ProductoClient;
import com.pedido.ms_pedido.dto.PedidoRequestDTO;
import com.pedido.ms_pedido.dto.PedidoResponseDTO;
import com.pedido.ms_pedido.dto.ProductoDTO;
import com.pedido.ms_pedido.exception.ResourceNotFoundException;
import com.pedido.ms_pedido.model.Pedido;
import com.pedido.ms_pedido.repository.PedidoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ProductoClient productoClient;

    public PedidoResponseDTO crearPedido(
            PedidoRequestDTO requestDTO) {

        ProductoDTO producto =
                productoClient.obtenerProducto(
                        requestDTO.getProductoId());

        Pedido pedido = new Pedido();

        pedido.setProductoId(
                requestDTO.getProductoId());

        pedido.setCliente(
                requestDTO.getCliente());

        pedido.setCantidad(
                requestDTO.getCantidad());

        pedido.setTotal(
                producto.getPrecio()
                        * requestDTO.getCantidad());

        Pedido guardado =
                pedidoRepository.save(pedido);

        return convertirDTO(
                guardado,
                producto.getNombre());
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO>
    obtenerTodos() {

        return pedidoRepository.findAll()
                .stream()
                .map(pedido -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    pedido.getProductoId());

                    return convertirDTO(
                            pedido,
                            producto.getNombre());
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO
    obtenerPorId(Long id) {

        Pedido pedido =
                pedidoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pedido no encontrado"));

        ProductoDTO producto =
                productoClient.obtenerProducto(
                        pedido.getProductoId());

        return convertirDTO(
                pedido,
                producto.getNombre());
    }

    public PedidoResponseDTO actualizarEstado(
            Long id,
            String estado) {

        Pedido pedido =
                pedidoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pedido no encontrado"));

        pedido.setEstado(estado);

        Pedido actualizado =
                pedidoRepository.save(pedido);

        ProductoDTO producto =
                productoClient.obtenerProducto(
                        actualizado.getProductoId());

        return convertirDTO(
                actualizado,
                producto.getNombre());
    }

    public void eliminarPedido(Long id) {

        Pedido pedido =
                pedidoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pedido no encontrado"));

        pedidoRepository.delete(pedido);
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO>
    buscarPorCliente(String cliente) {

        return pedidoRepository
                .findByClienteContainingIgnoreCase(
                        cliente)
                .stream()
                .map(pedido -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    pedido.getProductoId());

                    return convertirDTO(
                            pedido,
                            producto.getNombre());
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO>
    buscarPorEstado(String estado) {

        return pedidoRepository
                .findByEstado(estado)
                .stream()
                .map(pedido -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    pedido.getProductoId());

                    return convertirDTO(
                            pedido,
                            producto.getNombre());
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO>
    buscarPorProducto(Long productoId) {

        return pedidoRepository
                .findByProductoId(productoId)
                .stream()
                .map(pedido -> {

                    ProductoDTO producto =
                            productoClient.obtenerProducto(
                                    pedido.getProductoId());

                    return convertirDTO(
                            pedido,
                            producto.getNombre());
                })
                .toList();
    }

    private PedidoResponseDTO convertirDTO(
            Pedido pedido,
            String nombreProducto) {

        PedidoResponseDTO dto =
                new PedidoResponseDTO();

        dto.setId(pedido.getId());

        dto.setProductoId(
                pedido.getProductoId());

        dto.setNombreProducto(
                nombreProducto);

        dto.setCliente(
                pedido.getCliente());

        dto.setCantidad(
                pedido.getCantidad());

        dto.setTotal(
                pedido.getTotal());

        dto.setEstado(
                pedido.getEstado());

        dto.setFechaPedido(
                pedido.getFechaPedido());

        return dto;
    }
}
