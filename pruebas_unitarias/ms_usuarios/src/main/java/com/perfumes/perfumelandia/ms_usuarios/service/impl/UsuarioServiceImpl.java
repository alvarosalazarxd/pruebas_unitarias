package com.perfumes.perfumelandia.ms_usuarios.service.impl;

import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioRequestDTO;
import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioResponseDTO;
import com.perfumes.perfumelandia.ms_usuarios.exception.ResourceNotFoundException;
import com.perfumes.perfumelandia.ms_usuarios.model.Usuario;
import com.perfumes.perfumelandia.ms_usuarios.repository.UsuarioRepository;
import com.perfumes.perfumelandia.ms_usuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponseDTO crearUsuario(
            UsuarioRequestDTO requestDTO) {

        Usuario usuario = new Usuario();

        usuario.setNombre(requestDTO.getNombre());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setContrasena(requestDTO.getContrasena());
        usuario.setRol(requestDTO.getRol());
        usuario.setActivo(requestDTO.getActivo());
        usuario.setSucursal(requestDTO.getSucursal());

        Usuario guardado =
                usuarioRepository.save(usuario);

        return convertirDTO(guardado);
    }

    @Override
    public UsuarioResponseDTO obtenerUsuarioPorId(
            Long id) {

        Usuario usuario =
                usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"));

        return convertirDTO(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> obtenerTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }
    @Override
public UsuarioResponseDTO actualizarUsuario(
        Long id,
        UsuarioRequestDTO requestDTO) {

    Usuario usuario =
            usuarioRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Usuario no encontrado"));

    usuario.setNombre(requestDTO.getNombre());
    usuario.setEmail(requestDTO.getEmail());
    usuario.setContrasena(requestDTO.getContrasena());
    usuario.setRol(requestDTO.getRol());
    usuario.setActivo(requestDTO.getActivo());
    usuario.setSucursal(requestDTO.getSucursal());

    Usuario actualizado =
            usuarioRepository.save(usuario);

    return convertirDTO(actualizado);
}
    @Override
    public void eliminarUsuario(Long id) {

        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO convertirDTO(
            Usuario usuario) {

        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .activo(usuario.getActivo())
                .sucursal(usuario.getSucursal())
                .build();
    }

}
