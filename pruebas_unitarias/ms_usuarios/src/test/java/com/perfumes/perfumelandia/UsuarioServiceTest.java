package com.perfumes.perfumelandia;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
    @Test
        @DisplayName("obtenerUsuarioPorId: valida mapeo completo del DTO")
        void obtenerUsuarioPorId_mapeaCorrectamente() {

        Usuario usuario = TestDataFactory.crearUsuarioAleatorio();

        when(usuarioRepository.findById(usuario.getId()))
                .thenReturn(Optional.of(usuario));

        UsuarioResponseDTO dto =
                usuarioService.obtenerUsuarioPorId(usuario.getId());

        assertThat(dto.getId()).isEqualTo(usuario.getId());
        assertThat(dto.getNombre()).isEqualTo(usuario.getNombre());
        assertThat(dto.getEmail()).isEqualTo(usuario.getEmail());
        assertThat(dto.getRol()).isEqualTo(usuario.getRol());
        assertThat(dto.getSucursal()).isEqualTo(usuario.getSucursal());
        assertThat(dto.getActivo()).isEqualTo(usuario.getActivo());
        }
        @Test
        @DisplayName("eliminarUsuario: llama deleteById exactamente una vez")
        void eliminarUsuario_verificaLlamada() {

        Long id = 1L;

        usuarioService.eliminarUsuario(id);

        verify(usuarioRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(usuarioRepository);
        }
        @Test
        @DisplayName("obtenerTodos: retorna lista vacía")
        void obtenerTodos_listaVacia() {

        when(usuarioRepository.findAll())
                .thenReturn(java.util.List.of());

        var resultado = usuarioService.obtenerTodos();

        assertThat(resultado).isEmpty();
        }
                

        @Test
        @DisplayName("crearUsuario: verifica contenido del objeto enviado al save")
        void crearUsuario_verificaContenidoUsuario() {

        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNombre("Pedro");
        dto.setEmail("pedro@gmail.com");
        dto.setContrasena("1234");
        dto.setRol("USER");
        dto.setActivo(true);
        dto.setSucursal("Santiago");

        when(usuarioRepository.save(any(Usuario.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        usuarioService.crearUsuario(dto);

        ArgumentCaptor<Usuario> captor =
                ArgumentCaptor.forClass(Usuario.class);

        verify(usuarioRepository).save(captor.capture());

        Usuario usuarioGuardado = captor.getValue();

        assertThat(usuarioGuardado.getNombre()).isEqualTo("Pedro");
        assertThat(usuarioGuardado.getEmail()).isEqualTo("pedro@gmail.com");
        }
        @Test
        public void testActualizarUsuarioExistente() {
        // 1. Arrange: Prepara los datos
        Long id = 1L;
        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNombre("Nuevo Nombre");
        
        Usuario usuarioExistente = new Usuario(); // 
        usuarioExistente.setId(id);
        
        // Simulamos que el repositorio encuentra el usuario
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioExistente);

        // 2. Act: Llamas al método que está en rojo
        usuarioService.actualizarUsuario(id, request);

        // 3. Assert: Verificas que se llamó al guardado
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        }
    
}
