package com.producto.ms_producto;

 import com.producto.ms_producto.model.Producto;


import net.datafaker.Faker;

public class TestDataFactory {
    private static final Faker faker = new Faker();

    public static Producto crearProductoAleatorio() {
        Producto p = new Producto();
        p.setId(faker.number().randomNumber());
        p.setNombre(faker.commerce().productName());
        p.setPrecio(faker.number().randomDouble(2, 10, 1000));
        return p;
    }
}
    