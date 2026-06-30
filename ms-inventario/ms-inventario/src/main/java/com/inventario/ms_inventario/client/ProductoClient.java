package com.inventario.ms_inventario.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.inventario.ms_inventario.dto.ProductoDTO;

@FeignClient(
        name = "ms-productos",
        url = "${productos.service.url}"
)

public interface ProductoClient {

    @GetMapping("/api/productos/{id}")
    ProductoDTO obtenerProducto(
            @PathVariable Long id);
}