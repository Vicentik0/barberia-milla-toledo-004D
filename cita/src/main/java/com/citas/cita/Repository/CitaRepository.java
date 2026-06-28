package com.citas.cita.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citas.cita.model.Cita;


@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
