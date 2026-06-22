package com.promociones.promocion.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Nombre de la promoción", example = "Corte + Barba Completa")
    private String nombre;
    @Schema(description = "Descuento en porcentaje", example = "15.0")
    private Double descuento;
    @Schema(description = "Descripción de la oferta", example = "Válido solo los días martes")
    private String descripcion;
    @Schema(description = "Estado de la promoción", example = "true")
    private Boolean activa;
}