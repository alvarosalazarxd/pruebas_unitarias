package com.pedido.ms_pedido.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedido.ms_pedido.model.Pedido;

import java.util.List;

@Repository
public interface PedidoRepository
        extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteContainingIgnoreCase(
            String cliente);

    List<Pedido> findByEstado(
            String estado);

    List<Pedido> findByProductoId(
            Long productoId);
}
