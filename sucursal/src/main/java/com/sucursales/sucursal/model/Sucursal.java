package com.sucursales.sucursal.model;

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
@Table(name = "sucursales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo que representa una sucursal de la barbería")
public class Sucursal 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único autoincremental de la sucursal", example = "1")
    private Long idSucursal;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema( description = "Nombre de la sucursal",example = "Sucursal Centro",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 5, max = 150, message = "La dirección debe tener entre 5 y 150 caracteres")
    @Schema( description = "Dirección física de la sucursal",example = "Avenida Principal 123, Santiago",requiredMode = Schema.RequiredMode.REQUIRED)
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 8, max = 15, message = "El teléfono debe tener entre 8 y 15 caracteres")
    @Schema(description = "Teléfono de contacto de la sucursal",example = "912345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String telefono;
}