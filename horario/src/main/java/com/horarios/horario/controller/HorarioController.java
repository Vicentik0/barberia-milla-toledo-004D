package com.horarios.horario.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.horarios.horario.model.Horario;
import com.horarios.horario.service.HorarioService;

@RestController
@RequestMapping("api/v1/horarios")
@CrossOrigin(origins = "*")
@Tag(name = "Horarios", description = "Gestión de horarios de la barbería")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    @Operation(summary = "Listar todos los horarios")
    public List<Horario> listar() {
        return horarioService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un horario por su ID")
    public Horario buscarPorId(@PathVariable Long id) {
        return horarioService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo horario")
    public Horario guardar(@RequestBody Horario horario) {
        return horarioService.guardarHorario(horario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un horario existente")
    public Horario actualizar(@PathVariable Long id, @RequestBody Horario horario) {
        return horarioService.actualizarHorario(id, horario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un horario por ID")
    public void eliminar(@PathVariable Long id) {
        horarioService.eliminar(id);
    }
}