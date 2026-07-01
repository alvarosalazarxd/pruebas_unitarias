package com.example.ms_carrito.repository;

import com.example.ms_carrito.model.Carrito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CarritoRepository
        extends JpaRepository<Carrito, Long> {

    
    List<Carrito> findByUsuarioId(Long usuarioId);

    
    List<Carrito> findByTotalGreaterThan(Double total);

    
    List<Carrito> findByTotalLessThan(Double total);

    
    List<Carrito> findByCantidadProductosGreaterThan(
            Integer cantidadProductos);

    
    Optional<Carrito> findFirstByUsuarioId(Long usuarioId);

    
    boolean existsByUsuarioId(Long usuarioId);
}