package com.citas.cita.controller;

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

import com.citas.cita.config.ErrorResponse;
import com.citas.cita.model.Cita;
import com.citas.cita.service.CitaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/citas")
@Tag(name = "Citas",description = "Operaciones relacionadas con la gestión de citas")
public class CitaController {
    @Autowired
    private CitaService citaService;


    @Operation(summary = "Obtener todas las citas",description = "Retorna una lista con todas las citas registradas")
    @GetMapping
    public List<Cita> listar() {
        return citaService.listar();
    }

    @Operation(
        summary = "Obtener cita por ID",
        description = "Retorna una cita según su identificador"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Cita encontrada"),
        @ApiResponse(responseCode = "404",description = "Cita no encontrada",content = @Content(schema = @Schema(implementation = ErrorResponse.class) ))
})
@GetMapping("/{id}")
public Cita buscarPorId(@PathVariable Long id) {

    Cita cita = citaService.buscarPorId(id);

    if (cita == null) {
        throw new RuntimeException("Cita no encontrada");
    }

    return cita;
}

@Operation(summary = "Crear una nueva cita", description = "Registra una nueva cita en el sistema")
@ApiResponses(value = {
        @ApiResponse( responseCode = "200",description = "Cita creada correctamente"),
        @ApiResponse(responseCode = "400",description = "Datos inválidos")
})
    @PostMapping
    public Cita guardar(@Valid @RequestBody Cita cita) {
        return citaService.guardarCita(cita);
}


@Operation(summary = "Actualizar una cita",description = "Actualiza los datos de una cita según su identificador")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Cita actualizada correctamente"),
        @ApiResponse(responseCode = "400",description = "Datos inválidos"),
        @ApiResponse(responseCode = "404",description = "Cita no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
})
@PutMapping("/{id}")
    public Cita actualizar(@PathVariable Long id,@Valid @RequestBody Cita cita) {

        Cita actualizada = citaService.actualizarCita(id, cita);

    if (actualizada == null) {throw new RuntimeException("Cita no encontrada");}

    return actualizada;
}

    @Operation(summary = "Eliminar una cita")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
    return ResponseEntity.noContent().build();
}

}
