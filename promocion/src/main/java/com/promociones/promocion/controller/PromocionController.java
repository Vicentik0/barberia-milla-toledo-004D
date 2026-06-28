package com.promociones.promocion.controller;

import com.promociones.promocion.model.Promocion;
import com.promociones.promocion.service.PromocionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/promociones")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Tag(name = "Promociones", description = "Endpoint para la gestión de promociones con HATEOAS")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @PostMapping
    @Operation(summary = "Crear una nueva promoción")
    public Promocion crear(@RequestBody Promocion promocion) {
        return promocionService.guardarPromocion(promocion);
    }

    @GetMapping
    @Operation(summary = "Listar todas las promociones")
    public List<Promocion> listar() {
        return promocionService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener promoción con link al API Gateway")
    public EntityModel<Promocion> obtenerUna(@PathVariable Long id) {
        Promocion promocion = promocionService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Promoción no encontrada con ID: " + id));

        EntityModel<Promocion> recurso = EntityModel.of(promocion);

        recurso.add(linkTo(methodOn(PromocionController.class).obtenerUna(id)).withSelfRel());

        String urlGateway = "http://localhost:8099/api/v1/promociones/" + promocion.getId();
        Link linkGateway = Link.of(urlGateway, "detalle-gateway");
        recurso.add(linkGateway);

        return recurso;
    }
}
