package com.barberos.barbero.controller;

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

import com.barberos.barbero.model.Barbero;
import com.barberos.barbero.service.BarberoService;

@RestController
@RequestMapping("/barberos")
public class BarberoController 
{
    @Autowired
    private BarberoService barberoService;

    @GetMapping
    public List<Barbero> listar()
    {
        return barberoService.listar();
    }

    @GetMapping("/{id}")
    public Barbero buscarPorId(@PathVariable Long id)
    {
        return barberoService.buscarPorId(id);
    }

    @PostMapping
    public Barbero guardar(@RequestBody Barbero barbero)
    {
        return barberoService.guardarBarbero(barbero);
    }

    @PutMapping("/{id}")
    public Barbero actualizar(@PathVariable Long id, @RequestBody Barbero barbero)
    {
        return barberoService.actualizarBarbero(id, barbero);
    }

    @DeleteMapping("/{id}")
    public void eliminar (@PathVariable Long id)
    {
        barberoService.eliminar(id);
    }
}
