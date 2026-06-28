package com.sucursales.sucursal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sucursales.sucursal.model.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Long>
{

}
