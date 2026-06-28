package com.perfumes.perfumelandia;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.perfumes.perfumelandia.ms_usuarios.dto.UsuarioResponseDTO;
import com.perfumes.perfumelandia.ms_usuarios.model.Usuario;
import com.perfumes.perfumelandia.ms_usuarios.repository.UsuarioRepository;
import com.perfumes.perfumelandia.ms_usuarios.service.impl.UsuarioServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
}
