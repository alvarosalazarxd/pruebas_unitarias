package com.resena.ms_resena.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.resena.ms_resena.dto.ProductoDTO;

@FeignClient(
        name = "ms-productos",
        url = "${productos.service.url}"
)

public interface ProductoClient {

    @GetMapping("/api/productos/{id}")
    ProductoDTO   obtenerProducto(
            @PathVariable Long id);
}