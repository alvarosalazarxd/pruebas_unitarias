package com.resena.ms_resena;

import com.resena.ms_resena.model.Resena;

import net.datafaker.Faker;

public class ResenaDataFactory {
    private static final Faker faker = new Faker();

    public static Resena crearResenaAleatoria() {
        Resena resena = new Resena();
        resena.setProductoId(faker.number().randomNumber());
        resena.setUsuario(faker.name().username());
        resena.setCalificacion(faker.number().numberBetween(1, 6));
        resena.setComentario(faker.lorem().sentence());
        return resena;
    }
}
