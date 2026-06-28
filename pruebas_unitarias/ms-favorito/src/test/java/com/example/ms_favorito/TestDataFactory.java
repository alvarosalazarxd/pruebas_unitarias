package com.example.ms_favorito;

import com.example.ms_favorito.model.Favorito;
import net.datafaker.Faker;
import java.time.LocalDateTime;

/**
 * Fábrica de datos de prueba para el microservicio de Favoritos.
 */
public class TestDataFactory {

    private static final Faker faker = new Faker();

    // Genera un objeto Favorito con datos aleatorios
    public static Favorito unFavorito(Long usuarioId, String nombreProducto, Long productoId) {
        Favorito f = new Favorito();
        f.setId(faker.number().numberBetween(1L, 999L));
        f.setUsuarioId(usuarioId);
        f.setProductoId(productoId);
        f.setNombreProducto(nombreProducto);
        f.setFechaAgregado(LocalDateTime.now());
        return f;
    }

    // Genera un objeto Favorito totalmente aleatorio (útil para pruebas masivas)
    public static Favorito unFavoritoAleatorio() {
        return unFavorito(
            faker.number().numberBetween(1L, 100L),
            faker.commerce().productName(),
            faker.number().numberBetween(100L, 500L)
        );
    }
}