package com.cliente.clientes.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cliente.clientes.model.Cliente;
import com.cliente.clientes.repository.ClienteRepository;




@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    @DisplayName("Debería guardar un cliente correctamente")
    void guardarClienteTest() {

       
        Cliente cliente = new Cliente();
        cliente.setRut("12345678-9");
        cliente.setNombre("Carlos Pérez");
        cliente.setTelefono("912345678");

        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> {
            Cliente clienteGuardado = invocation.getArgument(0);
            clienteGuardado.setIdCliente(1L);
            return clienteGuardado;
        });

       
        Cliente resultado = clienteService.guardar(cliente);

        
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCliente());
        assertEquals("Carlos Pérez", resultado.getNombre());
        assertEquals("12345678-9", resultado.getRut());
        assertEquals("912345678", resultado.getTelefono());

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    @DisplayName("Debería buscar un cliente por ID correctamente")
    void buscarClientePorIdTest() {

        
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setRut("12345678-9");
        cliente.setNombre("Carlos Pérez");
        cliente.setTelefono("912345678");

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        
        Optional<Cliente> resultado = clienteService.buscarPorId(1L);

       
        assertNotNull(resultado);
        assertEquals(true, resultado.isPresent());
        assertEquals(1L, resultado.get().getIdCliente());
        assertEquals("Carlos Pérez", resultado.get().getNombre());
        assertEquals("12345678-9", resultado.get().getRut());
        assertEquals("912345678", resultado.get().getTelefono());

        verify(clienteRepository, times(1)).findById(1L);
    }

}
