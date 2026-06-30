package com.example.ms_catalogo.repository;

import com.example.ms_catalogo.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository
        extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(String categoria);

    List<Producto> findByMarca(String marca);

    List<Producto> findByNombreContainingIgnoreCase(
            String nombre);
}