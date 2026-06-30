package com.producto.ms_producto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producto.ms_producto.model.Producto;

import java.util.List;

@Repository
public interface ProductoRepository
        extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(
            String categoria);

    List<Producto> findByMarca(
            String marca);

    List<Producto> findByPrecioLessThanEqual(
            Double precio);

    List<Producto> findByNombreContainingIgnoreCase(
            String nombre);

    boolean existsByNombre(
            String nombre);
}
