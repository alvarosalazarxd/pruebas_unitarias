package com.example.ms_carrito;

import com.example.ms_carrito.dto.CarritoRequestDTO;
import com.example.ms_carrito.model.Carrito;
import net.datafaker.Faker;

import java.time.LocalDateTime;

public class TestDataFactory {

    private static final Faker faker = new Faker();

    private TestDataFactory() {
    }

    public static Carrito carrito() {
        return new Carrito(
                faker.number().numberBetween(1L, 999L),
                faker.number().numberBetween(1L, 100L),
                faker.number().randomDouble(2, 10000, 300000),
                faker.number().numberBetween(1, 10),
                LocalDateTime.now()
        );
    }

    public static Carrito carrito(Long id) {
        return new Carrito(
                id,
                faker.number().numberBetween(1L, 100L),
                faker.number().randomDouble(2, 10000, 300000),
                faker.number().numberBetween(1, 10),
                LocalDateTime.now()
        );
    }

    public static CarritoRequestDTO carritoRequest() {

        CarritoRequestDTO dto = new CarritoRequestDTO();

        dto.setUsuarioId(
                faker.number().numberBetween(1L, 100L)
        );

        dto.setTotal(
                faker.number().randomDouble(2, 10000, 300000)
        );

        dto.setCantidadProductos(
                faker.number().numberBetween(1, 10)
        );

        return dto;
    }
}