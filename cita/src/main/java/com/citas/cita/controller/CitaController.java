package com.citas.cita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citas.cita.model.Cita;
import com.citas.cita.service.CitaService;

@RestController
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Cita> listar() {
        return citaService.listar();
    }

    @GetMapping("/{id}")
    public Cita buscarPorId(@PathVariable Long id) {
        return citaService.buscarPorId(id);
    }

    @PostMapping
    public Cita guardar(@RequestBody Cita cita) {
        return citaService.guardarCita(cita);
    }

    @PutMapping("/{id}")
    public Cita actualizar(@PathVariable Long id, @RequestBody Cita cita) {
        return citaService.actualizarCita(id, cita);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
    }

}
