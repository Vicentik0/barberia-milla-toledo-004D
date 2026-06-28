package com.servicios.servicios.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicios.servicios.config.ErrorResponse;
import com.servicios.servicios.model.Servicio;
import com.servicios.servicios.service.ServicioService;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/servicios")
@Tag(name = "Servicios",description = "Operaciones relacionadas con los servicios ofrecidos por la barbería")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @Operation( summary = "Obtener todos los servicios", description = "Retorna una lista con todos los servicios disponibles")
    @GetMapping
     public List<Servicio> listar() {
         return servicioService.listar();
}

    @Operation(summary = "Obtener servicio por ID",description = "Retorna un servicio según su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Servicio encontrado"),
        @ApiResponse(responseCode = "404",description = "Servicio no encontrado",content = @Content( schema = @Schema(implementation = ErrorResponse.class)
                )
        )
})
    @GetMapping("/{id}")
    public Servicio buscarPorId(@PathVariable Long id) {

    Servicio servicio = servicioService.buscarPorId(id);

    if (servicio == null) {throw new RuntimeException("Servicio no encontrado");}

    return servicio;
}



    @Operation(
        summary = "Crear un nuevo servicio",
        description = "Registra un nuevo servicio ofrecido por la barbería"
)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Servicio creado correctamente"),
        @ApiResponse(responseCode = "400",description = "Datos inválidos")
})

    @PostMapping
    public Servicio guardar(@Valid @RequestBody Servicio servicio) {
        return servicioService.guardarServicio(servicio);
}

    @Operation(summary = "Actualizar un servicio",description = "Actualiza los datos de un servicio según su identificador")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200",description = "Servicio actualizado correctamente"),
        @ApiResponse( responseCode = "400",description = "Datos inválidos"),
        @ApiResponse(responseCode = "404",description = "Servicio no encontrado", content = @Content( schema = @Schema(implementation = ErrorResponse.class))
        )
})
    @PutMapping("/{id}")
    public Servicio actualizar(@PathVariable Long id,@Valid @RequestBody Servicio servicio) {

    Servicio actualizado = servicioService.actualizarServicio(id, servicio);
    if (actualizado == null) {
        throw new RuntimeException("Servicio no encontrado");}
    return actualizado;
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {servicioService.eliminar(id);
    return ResponseEntity.noContent().build();
}
    
    

}
