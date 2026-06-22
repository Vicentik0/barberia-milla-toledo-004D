package com.sucursales.sucursal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sucursales.sucursal.config.ErrorResponse;
import com.sucursales.sucursal.model.Sucursal;
import com.sucursales.sucursal.service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/sucursales")
@Tag(name = "Sucursales",description = "Operaciones relacionadas con la gestión de sucursales de la barbería")
public class SucursarlController 
{
    @Autowired
    private SucursalService sucursalService;

    @Operation( summary = "Obtener todas las sucursales",description = "Retorna una lista con todas las sucursales registradas")
    @GetMapping
    public List<Sucursal> listar() {
        return sucursalService.listar();
    }

    @Operation( summary = "Obtener sucursal por ID", description = "Retorna una sucursal según su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada" ),
            @ApiResponse( responseCode = "404", description = "Sucursal no encontrada",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public Sucursal buscarPorId(@PathVariable Long id) {

        Sucursal sucursal = sucursalService.buscarPorId(id);

        if (sucursal == null) {
            throw new RuntimeException("Sucursal no encontrada");
        }

        return sucursal;
    }

    @Operation(summary = "Crear una nueva sucursal",description = "Registra una nueva sucursal de la barbería")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200",description = "Sucursal creada correctamente" ),
            @ApiResponse(responseCode = "400",description = "Datos inválidos")
    })
    @PostMapping
    public Sucursal guardar(@Valid @RequestBody Sucursal sucursal) {
        return sucursalService.guardarSucursal(sucursal);
    }

    @Operation( summary = "Actualizar una sucursal", description = "Actualiza los datos de una sucursal según su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Sucursal actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse( responseCode = "404",description = "Sucursal no encontrada",content = @Content(schema = @Schema(implementation = ErrorResponse.class) ) )
    })
    @PutMapping("/{id}")
    public Sucursal actualizar(@PathVariable Long id,@Valid @RequestBody Sucursal sucursal) {

        Sucursal actualizada = sucursalService.actualizarSucursal(id, sucursal);

        if (actualizada == null) {
            throw new RuntimeException("Sucursal no encontrada");
        }

        return actualizada;
    }

    @Operation(summary = "Eliminar una sucursal")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sucursalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
