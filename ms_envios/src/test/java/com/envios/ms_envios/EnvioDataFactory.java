package com.envios.ms_envios;

import com.envios.ms_envios.model.Envio;
import net.datafaker.Faker;

import java.time.LocalDateTime;

public class EnvioDataFactory {

    private static final Faker faker = new Faker();

    public static Envio crearEnvioAleatorio() {

        Envio envio = new Envio();

        envio.setId(faker.number().numberBetween(1L,999L));
        envio.setPedidoId(faker.number().numberBetween(1L,100L));
        envio.setDireccion(faker.address().fullAddress());
        envio.setEstado("PREPARANDO");
        envio.setCodigoSeguimiento("ENV-12345678");
        envio.setTransportista(faker.company().name());
        envio.setFechaEnvio(LocalDateTime.now());

        return envio;
    }

}