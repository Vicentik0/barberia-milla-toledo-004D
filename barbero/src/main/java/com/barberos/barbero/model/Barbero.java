package com.barberos.barbero.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "barberos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Barbero 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarbero;

    private String nombre;
    private String especialidad;
    private String telefono;
}
