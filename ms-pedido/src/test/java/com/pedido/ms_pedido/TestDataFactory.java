package com.pedido.ms_pedido;

import com.pedido.ms_pedido.model.Pedido;
import net.datafaker.Faker;
import java.time.LocalDateTime;

public class TestDataFactory {
    private static final Faker faker = new Faker();

    public static Pedido unPedido(Long productoId, String cliente, Integer cantidad, Double precioUnitario) {
        Pedido p = new Pedido();
        p.setId(faker.number().numberBetween(1L, 999L));
        p.setProductoId(productoId);
        p.setCliente(cliente);
        p.setCantidad(cantidad);
        p.setTotal(cantidad * precioUnitario);
        p.setEstado("PENDIENTE");
        p.setFechaPedido(LocalDateTime.now());
        return p;
    }
}