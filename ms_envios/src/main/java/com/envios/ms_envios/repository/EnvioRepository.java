package com.envios.ms_envios.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envios.ms_envios.model.Envio;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

  
    Optional<Envio> findByCodigoSeguimiento(String codigoSeguimiento);

   
    List<Envio> findByEstado(String estado);

    
    List<Envio> findByTransportistaIgnoreCase(String transportista);

    
    List<Envio> findByPedidoId(Long pedidoId);

    
    List<Envio> findByDireccionContainingIgnoreCase(String direccion);

    
    boolean existsByCodigoSeguimiento(String codigoSeguimiento);
}
