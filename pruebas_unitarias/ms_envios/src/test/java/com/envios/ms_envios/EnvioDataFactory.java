package com.envios.ms_envios;

import com.envios.ms_envios.model.Envio;

import net.datafaker.Faker;

public class EnvioDataFactory {
    private static final Faker faker = new Faker();

    public static Envio crearEnvioAleatorio() {
        Envio envio = new Envio();
    
        envio.setPedidoId(faker.number().randomNumber());
        envio.setDireccion(faker.address().fullAddress());
        envio.setTransportista(faker.company().name());
        
        
        envio.setEstado("PREPARANDO");
        
        return envio;   
    }
}