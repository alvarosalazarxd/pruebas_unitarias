package com.producto.ms_producto;

import com.producto.ms_producto.model.Producto;
import net.datafaker.Faker;

public class TestDataFactory {

    private static final Faker faker = new Faker();

    public static Producto crearProductoAleatorio() {

        Producto p = new Producto();

        p.setId(faker.number().numberBetween(1L,999L));
        p.setNombre(faker.commerce().productName());
        p.setMarca(faker.company().name());
        p.setCategoria("Perfumes");
        p.setPrecio(faker.number().randomDouble(2,10,1000));
        p.setStock(faker.number().numberBetween(1,100));
        p.setDescripcion(faker.lorem().sentence());

        return p;
    }
}