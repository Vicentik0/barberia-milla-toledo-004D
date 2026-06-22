package com.barberos.barbero.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Schema(description = "Identificador único autoincremental del barbero", example = "1")
    private Long idBarbero;



    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre completo del barbero",example = "Carlos Pérez",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(min = 3, max = 100, message = "La especialidad debe tener entre 3 y 100 caracteres")
    @Schema(description = "Especialidad principal del barbero",example = "Cortes clásicos y degradados",requiredMode = Schema.RequiredMode.REQUIRED)
    private String especialidad;


    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 8, max = 15, message = "El teléfono debe tener entre 8 y 15 caracteres")
    @Schema(description = "Teléfono de contacto del barbero", example = "912345678",requiredMode = Schema.RequiredMode.REQUIRED)
    private String telefono;
}
