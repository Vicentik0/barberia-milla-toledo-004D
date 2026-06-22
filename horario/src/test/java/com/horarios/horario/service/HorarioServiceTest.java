package com.horarios.horario.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.horarios.horario.model.Horario;
import com.horarios.horario.repository.HorarioRepository;

@ExtendWith(MockitoExtension.class)
class HorarioServiceTest {

    @Mock
    private HorarioRepository horarioRepository;

    @InjectMocks
    private HorarioService horarioService;

    @Test
    void buscarPorIdExitosoTest() {
        Long horarioId = 1L;
        
        Horario horarioMock = new Horario();
        horarioMock.setIdHorario(horarioId);
        horarioMock.setDia("Lunes");
        horarioMock.setHoraInicio("09:00");
        horarioMock.setHoraFin("10:00");
        horarioMock.setDisponible(true);

        when(horarioRepository.findById(horarioId)).thenReturn(Optional.of(horarioMock));

        // Ejecución
        Horario resultado = horarioService.buscarPorId(horarioId);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals("Lunes", resultado.getDia());
        assertEquals("09:00", resultado.getHoraInicio());
        verify(horarioRepository, times(1)).findById(horarioId);
    }

    @Test
    void buscarPorIdNoEncontradoThrowsExceptionTest() {
        Long horarioId = 99L;

        when(horarioRepository.findById(horarioId)).thenReturn(Optional.empty());

        // Verifica que lance la RuntimeException que configuramos en el servicio
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            horarioService.buscarPorId(horarioId);
        });

        assertEquals("Horario no encontrado con ID: " + horarioId, excepcion.getMessage());
        verify(horarioRepository, times(1)).findById(horarioId);
    }
}
