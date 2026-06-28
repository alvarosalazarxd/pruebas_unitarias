package com.perfumes.perfumelandia;

import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioRequestDTO;
import com.perfumes.perfumelandia.ms_usuarios.model.Usuario;

import net.datafaker.Faker;

public class TestDataFactory {

    private static final Faker faker = new Faker();

  public static Usuario unUsuario() {
        Usuario u = new Usuario();
        
       
        u.setId(faker.number().numberBetween(1L, 999L));
        u.setNombre(faker.name().fullName()); 
        u.setEmail(faker.internet().emailAddress()); 
        u.setContrasena(faker.internet().password()); 
        u.setRol("USER"); 
        u.setActivo(true);
        u.setSucursal("Sucursal Central");
        
        return u;
    }


    public static UsuarioRequestDTO unUsuarioRequest() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNombre(faker.name().username());
        return dto;
    }

    public static Usuario crearUsuarioAleatorio() {
    // Esto llama al método que ya configuramos con faker
    return unUsuario(); 
}
}