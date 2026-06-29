package com.perfumes.perfumelandia;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioRequestDTO;
import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioResponseDTO;
import com.perfumes.perfumelandia.ms_usuarios.model.Usuario;
import com.perfumes.perfumelandia.ms_usuarios.repository.UsuarioRepository;
import com.perfumes.perfumelandia.ms_usuarios.service.impl.UsuarioServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UsuarioService - Pruebas Unitarias")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    @DisplayName("obtenerPorId: retorna el usuario cuando existe")
    void obtenerPorId_usuarioExiste_retornaDTO() {
        // Arrange
        Usuario usuario = TestDataFactory.crearUsuarioAleatorio();
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        // Act
        UsuarioResponseDTO resultado = usuarioService.obtenerUsuarioPorId(usuario.getId());

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo(usuario.getNombre());
        verify(usuarioRepository, times(1)).findById(usuario.getId());
    }
    @Test
    @DisplayName("crearUsuario: guarda correctamente")
    void crearUsuario_guardadoCorrectamente() {

        UsuarioRequestDTO dto = new UsuarioRequestDTO();

        dto.setNombre("Alvaro");
        dto.setEmail("alvaro@gmail.com");
        dto.setContrasena("1234");
        dto.setRol("ADMIN");
        dto.setActivo(true);
        dto.setSucursal("Concepcion");

        Usuario usuario = new Usuario();

        usuario.setId(1L);
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(dto.getContrasena());
        usuario.setRol(dto.getRol());
        usuario.setActivo(dto.getActivo());
        usuario.setSucursal(dto.getSucursal());

        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuario);

        UsuarioResponseDTO resultado =
                usuarioService.crearUsuario(dto);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre())
                .isEqualTo("Alvaro");
    }
    @Test
    @DisplayName("obtenerTodos retorna lista")
    void obtenerTodos() {

        Usuario usuario =
                TestDataFactory.crearUsuarioAleatorio();

        when(usuarioRepository.findAll())
                .thenReturn(java.util.List.of(usuario));

        assertThat(usuarioService.obtenerTodos())
                .hasSize(1);
    }
    @Test
    @DisplayName("eliminarUsuario")
    void eliminarUsuario() {

        usuarioService.eliminarUsuario(1L);

        verify(usuarioRepository)
                .deleteById(1L);
    }
    @Test
    @DisplayName("usuario inexistente")
    void usuarioNoExiste() {

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThat(org.junit.jupiter.api.Assertions
                .assertThrows(RuntimeException.class,
                        () -> usuarioService.obtenerUsuarioPorId(1L)))
                .isNotNull();
    }
    @Test
    void verificarSave() {

        UsuarioRequestDTO dto =
                TestDataFactory.unUsuarioRequest();

        dto.setEmail("correo@gmail.com");
        dto.setContrasena("1234");
        dto.setRol("USER");
        dto.setActivo(true);

        Usuario usuario =
                TestDataFactory.crearUsuarioAleatorio();

        when(usuarioRepository.save(any()))
                .thenReturn(usuario);

        usuarioService.crearUsuario(dto);

        verify(usuarioRepository,
                times(1))
                .save(any(Usuario.class));
    }
    @Test
    void nombreCorrecto() {

        Usuario usuario =
                TestDataFactory.crearUsuarioAleatorio();

        when(usuarioRepository.findById(usuario.getId()))
                .thenReturn(Optional.of(usuario));

        UsuarioResponseDTO dto =
                usuarioService.obtenerUsuarioPorId(
                        usuario.getId());

        assertThat(dto.getNombre())
                .isEqualTo(usuario.getNombre());
    }
    @Test
    void emailCorrecto() {

        Usuario usuario =
                TestDataFactory.crearUsuarioAleatorio();

        when(usuarioRepository.findById(usuario.getId()))
                .thenReturn(Optional.of(usuario));

        UsuarioResponseDTO dto =
                usuarioService.obtenerUsuarioPorId(
                        usuario.getId());

        assertThat(dto.getEmail())
                .isEqualTo(usuario.getEmail());
    }
    @Test
    void rolCorrecto() {

        Usuario usuario =
                TestDataFactory.crearUsuarioAleatorio();

        when(usuarioRepository.findById(usuario.getId()))
                .thenReturn(Optional.of(usuario));

        UsuarioResponseDTO dto =
                usuarioService.obtenerUsuarioPorId(
                        usuario.getId());

        assertThat(dto.getRol())
                .isEqualTo(usuario.getRol());
    }
}
