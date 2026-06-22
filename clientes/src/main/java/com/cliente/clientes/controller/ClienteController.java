package com.cliente.clientes.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cliente.clientes.config.ErrorResponse;
import com.cliente.clientes.model.Cliente;
import com.cliente.clientes.service.ClienteService;
import org.springframework.lang.NonNull;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Clientes",description = "Operaciones relacionadas con la gestión de clientes con soporte HATEOAS")
@SuppressWarnings("null")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtener todos los clientes", description = "Retorna una colección de clientes con enlaces a recursos relacionados")
    @GetMapping
    public CollectionModel<EntityModel<Cliente>> listar() {

        List<EntityModel<Cliente>> clientes = clienteService.listar().stream()
                .map(cliente -> EntityModel.of(
                        cliente,
                        linkTo(methodOn(ClienteController.class).obtener(cliente.getIdCliente())).withSelfRel(),
                        linkTo(methodOn(ClienteController.class).listar()) .withRel("clientes")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(
                clientes,
                linkTo(methodOn(ClienteController.class) .listar()).withSelfRel()
        );
    }

    @Operation(
            summary = "Obtener cliente por ID",
            description = "Retorna un cliente individual con enlaces a acciones relacionadas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404",description = "El cliente no existe",content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{id}")
    public EntityModel<Cliente> obtener(@NonNull @PathVariable Long id) {

        Cliente cliente = clienteService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return EntityModel.of(
                cliente,
                linkTo(methodOn(ClienteController.class) .obtener(id)) .withSelfRel(),
                linkTo(methodOn(ClienteController.class).listar()) .withRel("todos-los-clientes"),
                linkTo(methodOn(ClienteController.class) .eliminar(id)) .withRel("eliminar")
        );
    }

    @Operation(summary = "Crear un nuevo cliente")
    @PostMapping
    public ResponseEntity<EntityModel<Cliente>> crear(@NonNull @Valid @RequestBody Cliente cliente) {

        Cliente nuevoCliente = clienteService.guardarProducto(cliente);

        EntityModel<Cliente> recurso = EntityModel.of(
                nuevoCliente,
                linkTo(methodOn(ClienteController.class) .obtener(nuevoCliente.getIdCliente())).withSelfRel()
        );

        return ResponseEntity.ok(recurso);
    }

    @Operation(summary = "Eliminar un cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@NonNull @PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
