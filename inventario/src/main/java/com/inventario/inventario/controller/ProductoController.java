package com.inventario.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.inventario.config.ErrorResponse;
import com.inventario.inventario.model.Producto;
import com.inventario.inventario.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/productos")
@Tag(name = "Inventario", description = "Operaciones relacionadas con los productos disponibles en inventario")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Obtener todos los productos",description = "Retorna una lista con todos los productos del inventario")
    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    @Operation(summary = "Obtener producto por ID",description = "Retorna un producto según su identificador" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "Producto encontrado" ),
            @ApiResponse( responseCode = "404",description = "Producto no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class) ))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo producto",description = "Registra un producto en el inventario" )
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200",description = "Producto creado correctamente" ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos" )
    })
    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardarProducto(producto));
    }

    @Operation(summary = "Eliminar un producto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    

}
