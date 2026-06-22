package com.barberos.barbero.controller;

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

import com.barberos.barbero.config.ErrorResponse;
import com.barberos.barbero.model.Barbero;
import com.barberos.barbero.service.BarberoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/barberos")
@Tag(name = "Barberos",description = "Operaciones relacionadas con la gestión de barberos")
public class BarberoController 
{
    @Autowired
    private BarberoService barberoService;

    @Operation(summary = "Obtener todos los barberos",description = "Retorna una lista con todos los barberos registrados")
    @GetMapping
    public List<Barbero> listar()
    {
        return barberoService.listar();
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Barbero encontrado"),
        @ApiResponse(responseCode = "404",description = "Barbero no encontrado",content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    @GetMapping("/{id}")
    public Barbero buscarPorId(@PathVariable Long id) {

    Barbero barbero = barberoService.buscarPorId(id);

    if (barbero == null) {
        throw new RuntimeException("Barbero no encontrado");
    }

    return barbero;
}




    @Operation(summary = "Crear un nuevo barbero",description = "Registra un nuevo barbero en el sistema")

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Barbero creado correctamente"),
        @ApiResponse(responseCode = "400",description = "Datos inválidos")
})
    @PostMapping
    public Barbero guardar(@Valid @RequestBody Barbero barbero) {
       return barberoService.guardarBarbero(barbero);
}




    @Operation(summary = "Actualizar un barbero",description = "Actualiza los datos de un barbero según su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Barbero actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404",description = "Barbero no encontrado",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public Barbero actualizar(@PathVariable Long id,@Valid @RequestBody Barbero barbero) {

        Barbero actualizado = barberoService.actualizarBarbero(id, barbero);

        if (actualizado == null) {throw new RuntimeException("Barbero no encontrado");}

           return actualizado;
}





    @Operation(summary = "Eliminar un barbero")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    barberoService.eliminar(id);
    return ResponseEntity.noContent().build();
}
}
