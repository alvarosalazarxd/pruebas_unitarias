package com.example.ms_notificaciones;

import com.example.ms_notificaciones.dto.NotificacionDTO;
import com.example.ms_notificaciones.model.Notificacion;
import com.example.ms_notificaciones.repository.NotificacionRepository;
import com.example.ms_notificaciones.service.impl.NotificacionServiceImpl;

import net.datafaker.Faker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class NotificacionServiceImplTest {

    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionServiceImpl service;

    private Faker faker;

    private Notificacion notificacion;
    private NotificacionDTO dto;

    @BeforeEach
    void setUp() {

        faker = new Faker();

        dto = new NotificacionDTO();
        dto.setUsuarioId(faker.number().randomNumber());
        dto.setMensaje(faker.lorem().sentence());
        dto.setTipo("EMAIL");

        notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setUsuarioId(dto.getUsuarioId());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setTipo(dto.getTipo());
        notificacion.setLeida(false);
        notificacion.setFechaCreacion(LocalDateTime.now());
    }

    @Test
    void crearNotificacion() {

        when(repository.save(any(Notificacion.class)))
                .thenReturn(notificacion);

        NotificacionDTO resultado =
                service.crearNotificacion(dto);

        assertNotNull(resultado);
        assertEquals(dto.getUsuarioId(), resultado.getUsuarioId());
        assertEquals(dto.getMensaje(), resultado.getMensaje());
        assertEquals(dto.getTipo(), resultado.getTipo());

        verify(repository).save(any(Notificacion.class));
    }

    @Test
    void buscarPorId() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(notificacion));

        NotificacionDTO resultado =
                service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        verify(repository).findById(1L);
    }

    @Test
    void listarTodas() {

        when(repository.findAll())
                .thenReturn(List.of(notificacion));

        List<NotificacionDTO> lista =
                service.listarTodas();

        assertEquals(1, lista.size());

        verify(repository).findAll();
    }
        @Test
    void buscarPorUsuario() {

        when(repository.findByUsuarioId(notificacion.getUsuarioId()))
                .thenReturn(List.of(notificacion));

        List<NotificacionDTO> lista =
                service.buscarPorUsuario(notificacion.getUsuarioId());

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(notificacion.getUsuarioId(),
                lista.get(0).getUsuarioId());

        verify(repository)
                .findByUsuarioId(notificacion.getUsuarioId());
    }

    @Test
    void buscarPorEstado() {

        when(repository.findByLeida(false))
                .thenReturn(List.of(notificacion));

        List<NotificacionDTO> lista =
                service.buscarPorEstado(false);

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertFalse(lista.get(0).getLeida());

        verify(repository).findByLeida(false);
    }

    @Test
    void marcarComoLeida() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(notificacion));

        when(repository.save(any(Notificacion.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        NotificacionDTO resultado =
                service.marcarComoLeida(1L);

        assertTrue(resultado.getLeida());

        verify(repository).findById(1L);
        verify(repository).save(any(Notificacion.class));
    }

    @Test
    void eliminar() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(notificacion));

        doNothing().when(repository)
                .delete(notificacion);

        service.eliminar(1L);

        verify(repository).findById(1L);
        verify(repository).delete(notificacion);
    }

}