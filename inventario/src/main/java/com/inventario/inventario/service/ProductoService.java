package com.inventario.inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.ProductoRepository;




@Service

public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List <Producto> listar(){
        return productoRepository.findAll();
    }
   public Producto guardarProducto(Producto producto){
    return productoRepository.save(producto);
    }


    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public void eliminar(Long id){
        productoRepository.deleteById(id);
    }



}
