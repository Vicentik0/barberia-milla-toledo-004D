package com.sucursales.sucursal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sucursales.sucursal.model.Sucursal;
import com.sucursales.sucursal.repository.SucursalRepository;

@Service
public class SucursalService 
{
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> listar() 
    {
        return sucursalRepository.findAll();
    }

    public Sucursal guardarSucursal(Sucursal sucursal) 
    {
        return sucursalRepository.save(sucursal);
    }

    public Sucursal buscarPorId(Long id) 
    {
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);

        if (sucursal != null) 
        {
            return sucursal;
        }

        return null;
    }

    public Sucursal actualizarSucursal(Long id, Sucursal sucursal) 
    {
        Sucursal sucursalExistente = sucursalRepository.findById(id).orElse(null);

        if (sucursalExistente != null) {

            sucursalExistente.setNombre(sucursal.getNombre());
            sucursalExistente.setDireccion(sucursal.getDireccion());
            sucursalExistente.setTelefono(sucursal.getTelefono());

            return sucursalRepository.save(sucursalExistente);
        }

        return null;
    }

    public void eliminar(Long id) 
    {
        sucursalRepository.deleteById(id);
    }
}