package com.resenas.resena.controller;

import com.resenas.resena.model.Resena;
import com.resenas.resena.service.GestionResenaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/resenas")
@CrossOrigin(origins = "*")
@Tag(name = "Reseñas", description = "Gestión de reseñas con WebClient y HATEOAS")
public class ResenaController {

    @Autowired
    private GestionResenaService gestionResenaService;

    @PostMapping
    @Operation(summary = "Crear una nueva reseña")
    public Resena crear(@RequestBody Resena resena) {
        return gestionResenaService.guardarResena(resena);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reseña completa con datos del barbero")
    public EntityModel<Resena> obtenerUna(@PathVariable Long id) {
        Resena resena = gestionResenaService.obtenerResenaCompleta(id);
        
        if (resena == null) {
            // El GlobalExceptionHandler capturará esto y responderá con el formato ErrorResponse (404)
            throw new RuntimeException("Reseña no encontrada con ID: " + id);
        }

        EntityModel<Resena> recurso = EntityModel.of(resena);
        recurso.add(linkTo(methodOn(ResenaController.class).obtenerUna(id)).withSelfRel());

        String urlGateway = "http://localhost:8099/api/v1/resenas/" + resena.getId();
        Link linkGateway = Link.of(urlGateway, "detalle-gateway");
        recurso.add(linkGateway);

        return recurso;
    }
}