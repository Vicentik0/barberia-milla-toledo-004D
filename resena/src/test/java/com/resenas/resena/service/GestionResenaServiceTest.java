package com.resenas.resena.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.resenas.resena.model.Resena;
import com.resenas.resena.repository.ResenaRepository;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class GestionResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private GestionResenaService gestionResenaService;

    @Test
    void obtenerResenaCompletaTest() {
        Long resenaId = 1L;
        Long barberoId = 200L;

        Resena resenaMock = new Resena();
        resenaMock.setId(resenaId);
        resenaMock.setBarberoId(barberoId);
        resenaMock.setEstrellas(5);
        resenaMock.setComentario("Excelente servicio");

        java.util.Map<String, Object> barberoMock = new java.util.HashMap<>();
        barberoMock.put("id", barberoId);
        barberoMock.put("nombre", "Don Pepe");

        when(resenaRepository.findById(resenaId)).thenReturn(Optional.of(resenaMock));

        
        WebClient webClient = Mockito.mock(WebClient.class);
        WebClient.RequestHeadersUriSpec uriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec headersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);

        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(barberoMock));

        // Ejecución del método
        Resena resultado = gestionResenaService.obtenerResenaCompleta(resenaId);

        // Verificaciones
        assertNotNull(resultado);
        assertNotNull(resultado.getDatosBarbero());
        
        @SuppressWarnings("unchecked")
        java.util.Map<String, Object> datosRetornados = (java.util.Map<String, Object>) resultado.getDatosBarbero();
        assertEquals("Don Pepe", datosRetornados.get("nombre"));

        verify(resenaRepository, times(1)).findById(resenaId);
    }

    @Test
    void obtenerResenaNoEncontradaTest() {
        Long resenaId = 999L;

        // Simulamos que la base de datos devuelve un Optional vacío
        when(resenaRepository.findById(resenaId)).thenReturn(Optional.empty());

        // Ejecución
        Resena resultado = gestionResenaService.obtenerResenaCompleta(resenaId);

        // Verificación de que devuelva null, lo que gatillará el 404 en el controlador
        assertNull(resultado);
        verify(resenaRepository, times(1)).findById(resenaId);
    }
}
