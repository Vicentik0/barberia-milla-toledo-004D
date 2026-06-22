package com.horarios.horario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.horarios.horario.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long>
{

}
