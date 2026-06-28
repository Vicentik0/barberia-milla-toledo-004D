package com.pago.pago.controller;

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

import com.pago.pago.config.ErrorResponse;
import com.pago.pago.model.Pagos;
import com.pago.pago.service.PagosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagos")
@Tag(name = "Pagos",description = "Operaciones relacionadas con la gestión de pagos de la barbería")
public class PagosController {

    @Autowired
    private PagosService pagoService;

    @Operation(summary = "Obtener todos los pagos",description = "Retorna una lista con todos los pagos registrados")
    @GetMapping
    public List<Pagos> listar() {
        return pagoService.listar();
    }

    @Operation(summary = "Obtener pago por ID", description = "Retorna un pago según su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado",content = @Content(  schema = @Schema(implementation = ErrorResponse.class))) })
    @GetMapping("/{id}")
    public Pagos buscarPorId(@PathVariable Long id) {
        Pagos pago = pagoService.buscarPorId(id);
        if (pago == null) {throw new RuntimeException("Pago no encontrado"); }
        return pago;
    }

    @Operation(summary = "Crear un nuevo pago", description = "Registra un nuevo pago asociado a una cita")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200",description = "Pago creado correctamente"),
            @ApiResponse( responseCode = "400", description = "Datos inválidos")})
    @PostMapping
    public Pagos guardar(@Valid @RequestBody Pagos pago) {
        return pagoService.guardarPago(pago);
    }

    @Operation(summary = "Actualizar un pago",description = "Actualiza los datos de un pago según su identificador" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Pago actualizado correctamente"),
            @ApiResponse( responseCode = "400", description = "Datos inválidos" ),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado",content = @Content(schema = @Schema(implementation = ErrorResponse.class) ))
    })
    @PutMapping("/{id}")
    public Pagos actualizar( @PathVariable Long id,@Valid @RequestBody Pagos pago) {
        Pagos actualizado = pagoService.actualizarPago(id, pago);
        if (actualizado == null) {
            throw new RuntimeException("Pago no encontrado");
        }

        return actualizado;
    }

    @Operation(summary = "Eliminar un pago")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
