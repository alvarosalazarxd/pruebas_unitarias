package com.envios.ms_envios;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.envios.ms_envios.dto.EnvioResponseDTO;
import com.envios.ms_envios.model.Envio;
import com.envios.ms_envios.repository.EnvioRepository;
import com.envios.ms_envios.service.impl.EnvioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioServiceImpl envioService;

    @Test
    
    public void testObtenerEnvioPorId() {
        
        Long idFalso = 1L;
        Envio entidadFalsa = new Envio(); 
        entidadFalsa.setId(idFalso);

        // ¡IMPORTANTE! El 'when' se hace sobre el REPOSITORIO, no sobre el servicio
        when(envioRepository.findById(idFalso)).thenReturn(Optional.of(entidadFalsa));

      
        EnvioResponseDTO resultado = envioService.obtenerEnvioPorId(idFalso);

        assertNotNull(resultado);
        assertEquals(idFalso, resultado.getId());
        
      
        verify(envioRepository, times(1)).findById(idFalso);
    }
}