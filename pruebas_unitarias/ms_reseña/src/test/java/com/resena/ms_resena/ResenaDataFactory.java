package com.resena.ms_resena;

import com.resena.ms_resena.model.Resena;

import net.datafaker.Faker;

public class ResenaDataFactory {
    private static final Faker faker = new Faker();

    public static Resena crearResenaAleatoria() {

        Resena resena = new Resena();

        resena.setId(faker.number().numberBetween(1L,999L));
        resena.setProductoId(faker.number().numberBetween(1L,100L));
        resena.setUsuario(faker.name().username());
        resena.setCalificacion(faker.number().numberBetween(1,6));
        resena.setComentario(faker.lorem().sentence());
        resena.setFechaResena(java.time.LocalDateTime.now());

        return resena;
    }
}