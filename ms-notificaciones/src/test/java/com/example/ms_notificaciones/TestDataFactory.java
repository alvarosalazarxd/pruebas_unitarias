package com.example.ms_notificaciones;

import com.example.ms_notificaciones.dto.NotificacionRequestDTO;
import com.example.ms_notificaciones.model.Notificacion;
import net.datafaker.Faker;

import java.time.LocalDateTime;

public class TestDataFactory {

    private static final Faker faker = new Faker();

    private TestDataFactory() {}

    public static Notificacion notificacion() {
        return new Notificacion(
                faker.number().numberBetween(1L, 999L),
                faker.number().numberBetween(1L, 100L),
                faker.lorem().sentence(),
                faker.options().option("PEDIDO", "PAGO", "ENVIO", "PROMOCION"),
                faker.bool().bool(),
                LocalDateTime.now()
        );
    }

    public static Notificacion notificacion(Long id) {
        return new Notificacion(
                id,
                faker.number().numberBetween(1L, 100L),
                faker.lorem().sentence(),
                faker.options().option("PEDIDO", "PAGO", "ENVIO", "PROMOCION"),
                faker.bool().bool(),
                LocalDateTime.now()
        );
    }

    public static NotificacionRequestDTO notificacionRequest() {

        NotificacionRequestDTO dto = new NotificacionRequestDTO();

        dto.setUsuarioId(
                faker.number().numberBetween(1L, 100L)
        );

        dto.setMensaje(
                faker.lorem().sentence()
        );

        dto.setTipo(
                faker.options().option("PEDIDO", "PAGO", "ENVIO", "PROMOCION")
        );

        return dto;
    }
}