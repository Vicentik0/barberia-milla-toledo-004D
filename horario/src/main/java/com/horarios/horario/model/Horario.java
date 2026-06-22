package com.horarios.horario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "horarios")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Horario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idHorario;

    private String dia;

    private String horaInicio;

    private String horaFin;

    private Boolean disponible;
}
