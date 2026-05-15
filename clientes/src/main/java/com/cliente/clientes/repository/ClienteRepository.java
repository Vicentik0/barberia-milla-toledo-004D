package com.cliente.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cliente.clientes.model.Cliente;

@Repository

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}


