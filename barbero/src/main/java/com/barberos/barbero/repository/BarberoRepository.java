package com.barberos.barbero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barberos.barbero.model.Barbero;
@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long>
{

}
