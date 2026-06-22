package com.cliente.clientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cliente.clientes.model.Cliente;
import com.cliente.clientes.repository.ClienteRepository;

@Service

public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List <Cliente> listar(){
        return clienteRepository.findAll();
    }
   public Cliente guardarProducto(Cliente cliente){
    return clienteRepository.save(cliente);
    }


    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public void eliminar(Long id){
        clienteRepository.deleteById(id);
    }
    public Cliente guardar(Cliente cliente) {
    return clienteRepository.save(cliente);
}

}
