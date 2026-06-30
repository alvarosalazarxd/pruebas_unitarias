package com.example.ms_favorito.client;

import com.example.ms_favorito.dto.ProductoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.Optional;

@Slf4j
@Component
public class ProductoClient {

    private final WebClient webClient;
    private final String baseUrl;

    public ProductoClient(WebClient.Builder webClientBuilder, 
                          @Value("${productos.ms.url:http://localhost:8080}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public Optional<ProductoDTO> buscarProducto(Long productoId) {
        try {
            ProductoDTO producto = webClient.get()
                    .uri("/api/productos/{id}", productoId)
                    .retrieve()
                    .bodyToMono(ProductoDTO.class)
                    .block(); 

            return Optional.ofNullable(producto);

        } catch (WebClientResponseException.NotFound e) {
            log.warn("Producto con ID {} no fue encontrado en el catálogo.", productoId);
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error al conectar con ms-productos en {} : {}", baseUrl, e.getMessage());
            throw new RuntimeException("El servicio de productos no está disponible");
        }
    }
}