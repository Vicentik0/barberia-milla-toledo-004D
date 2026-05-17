package com.sucursales.sucursal.controller;

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

import com.sucursales.sucursal.model.Sucursal;
import com.sucursales.sucursal.service.SucursalService;


@RestController
@RequestMapping("/sucursales")
public class SucursarlController 
{
    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> listar() 
    {
        return sucursalService.listar();
    }

    @GetMapping("/{id}")
    public Sucursal buscarPorId(@PathVariable Long id) 
    {
        return sucursalService.buscarPorId(id);
    }

    @PostMapping
    public Sucursal guardar(@RequestBody Sucursal sucursal) 
    {
        return sucursalService.guardarSucursal(sucursal);
    }

    @PutMapping("/{id}")
    public Sucursal actualizar(@PathVariable Long id, @RequestBody Sucursal sucursal) 
    {
        return sucursalService.actualizarSucursal(id, sucursal);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) 
    {
        sucursalService.eliminar(id);
    }
}
