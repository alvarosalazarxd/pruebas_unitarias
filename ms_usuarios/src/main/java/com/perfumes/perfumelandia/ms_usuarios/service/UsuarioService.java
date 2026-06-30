package com.perfumes.perfumelandia.ms_usuarios.service;


import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioRequestDTO;
import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioResponseDTO;

import java.util.List;


public interface UsuarioService {
      UsuarioResponseDTO crearUsuario(
            UsuarioRequestDTO requestDTO);

    UsuarioResponseDTO obtenerUsuarioPorId(
            Long id);

    List<UsuarioResponseDTO> obtenerTodos();
    
    UsuarioResponseDTO actualizarUsuario(
        Long id,
        UsuarioRequestDTO requestDTO);
    
        void eliminarUsuario(Long id);
}
