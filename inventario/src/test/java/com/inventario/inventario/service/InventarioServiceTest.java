package com.inventario.inventario.service;

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

import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    @DisplayName("Debería guardar un producto correctamente")
    void guardarProductoTest() {

        Producto producto = new Producto();
        producto.setCodigo("PROD-001");
        producto.setNombre("Cera para cabello");
        producto.setMarca("L'Oréal");
        producto.setPrecio(8500);
        producto.setCantidad(25);

        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> {
            Producto productoGuardado = invocation.getArgument(0);
            productoGuardado.setIdProducto(1L);
            return productoGuardado;
        });

        Producto resultado = productoService.guardarProducto(producto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProducto());
        assertEquals("PROD-001", resultado.getCodigo());
        assertEquals("Cera para cabello", resultado.getNombre());
        assertEquals("L'Oréal", resultado.getMarca());
        assertEquals(8500, resultado.getPrecio());
        assertEquals(25, resultado.getCantidad());

        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    @DisplayName("Debería buscar un producto por ID correctamente")
    void buscarProductoPorIdTest() {

        Producto producto = new Producto();
        producto.setIdProducto(1L);
        producto.setCodigo("PROD-001");
        producto.setNombre("Cera para cabello");
        producto.setMarca("L'Oréal");
        producto.setPrecio(8500);
        producto.setCantidad(25);

        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(true, resultado.isPresent());
        assertEquals(1L, resultado.get().getIdProducto());
        assertEquals("Cera para cabello", resultado.get().getNombre());
        assertEquals("PROD-001", resultado.get().getCodigo());

        verify(productoRepository, times(1)).findById(1L);
    }

}
