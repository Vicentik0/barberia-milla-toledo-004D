package com.promociones.promocion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.promociones.promocion.model.Promocion;
import com.promociones.promocion.repository.PromocionRepository;

@ExtendWith(MockitoExtension.class)
public class PromocionServiceTest {

    @Mock
    private PromocionRepository promocionRepository;

    @InjectMocks
    private PromocionService promocionService;

    private Promocion promocionMock;

    @BeforeEach
    void setUp() {
        // Preparamos una promoción de prueba con el campo 'activa' que agregamos
        promocionMock = new Promocion(1L, "Corte + Barba", 15.0, "Martes locos", true);
    }

    @Test
    void testListar() {
        // --- 1. PREPARACIÓN ---
        when(promocionRepository.findAll()).thenReturn(Arrays.asList(promocionMock));

        // --- 2. EJECUCIÓN ---
        List<Promocion> resultado = promocionService.listar();

        // --- 3. VERIFICACIÓN ---
        assertNotNull(resultado, "La lista no debe ser nula");
        assertEquals(1, resultado.size());
        assertEquals("Corte + Barba", resultado.get(0).getNombre());
        verify(promocionRepository, times(1)).findAll();
    }

    @Test
    void testGuardarPromocion() {
        // --- 1. PREPARACIÓN ---
        when(promocionRepository.save(any(Promocion.class))).thenReturn(promocionMock);

        // --- 2. EJECUCIÓN ---
        Promocion guardada = promocionService.guardarPromocion(promocionMock);

        // --- 3. VERIFICACIÓN ---
        assertNotNull(guardada, "La promoción guardada no debe ser nula");
        assertEquals(1L, guardada.getId());
        assertEquals(15.0, guardada.getDescuento());
        verify(promocionRepository, times(1)).save(promocionMock);
    }

    @Test
    void testBuscarPorIdExitoso() {
        // --- 1. PREPARACIÓN ---
        when(promocionRepository.findById(1L)).thenReturn(Optional.of(promocionMock));

        // --- 2. EJECUCIÓN ---
        Optional<Promocion> resultado = promocionService.buscarPorId(1L);

        // --- 3. VERIFICACIÓN ---
        assertTrue(resultado.isPresent(), "Debería encontrar la promoción");
        assertEquals("Corte + Barba", resultado.get().getNombre());
        verify(promocionRepository, times(1)).findById(1L);
    }
}
