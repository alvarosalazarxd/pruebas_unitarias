package com.pedido.ms_pedido.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.pedido.ms_pedido.dto.ProductoDTO;

@FeignClient(
        name = "ms-productos",
        url = "${productos.service.url}"
)

public interface ProductoClient {

    @GetMapping("/api/productos/{id}")
    ProductoDTO obtenerProducto(
            @PathVariable Long id);
}