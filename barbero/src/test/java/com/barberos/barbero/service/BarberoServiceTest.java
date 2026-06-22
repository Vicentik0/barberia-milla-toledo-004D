package com.barberos.barbero.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.barberos.barbero.model.Barbero;
import com.barberos.barbero.repository.BarberoRepository;

@ExtendWith(MockitoExtension.class)

public class BarberoServiceTest {

    @Mock
    private BarberoRepository barberoRepository;

    @InjectMocks
    private BarberoService barberoService;

    @Test
    @DisplayName("Debería guardar un barbero correctamente")
    void guardarBarberoTest() {

        Barbero barbero = new Barbero();
        barbero.setNombre("Carlos Pérez");
        barbero.setEspecialidad("Cortes clásicos");
        barbero.setTelefono("912345678");

        when(barberoRepository.save(any(Barbero.class))).thenAnswer(invocation -> {
            Barbero barberoGuardado = invocation.getArgument(0);
            barberoGuardado.setIdBarbero(1L);
            return barberoGuardado;
        });

        Barbero resultado = barberoService.guardarBarbero(barbero);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdBarbero());
        assertEquals("Carlos Pérez", resultado.getNombre());
        assertEquals("Cortes clásicos", resultado.getEspecialidad());
        assertEquals("912345678", resultado.getTelefono());

        verify(barberoRepository, times(1)).save(barbero);
    }

    @Test
    @DisplayName("Debería buscar un barbero por ID correctamente")
    void buscarBarberoPorIdTest() {

        Barbero barbero = new Barbero();
        barbero.setIdBarbero(1L);
        barbero.setNombre("Carlos Pérez");
        barbero.setEspecialidad("Cortes clásicos");
        barbero.setTelefono("912345678");

        when(barberoRepository.findById(1L)).thenReturn(Optional.of(barbero));

        Barbero resultado = barberoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdBarbero());
        assertEquals("Carlos Pérez", resultado.getNombre());
        assertEquals("Cortes clásicos", resultado.getEspecialidad());
        assertEquals("912345678", resultado.getTelefono());

        verify(barberoRepository, times(1)).findById(1L);
    }

}
