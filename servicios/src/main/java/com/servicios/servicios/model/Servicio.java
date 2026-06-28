package com.servicios.servicios.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name= "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo que representa un servicio ofrecido por la barbería")

public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único autoincremental del servicio", example = "1")
    private Long idServicio;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre del servicio de barbería",example = "Corte de cabello", requiredMode = Schema.RequiredMode.REQUIRED )
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    @Schema(description = "Descripción del servicio",example = "Corte clásico con terminaciones a máquina",requiredMode = Schema.RequiredMode.REQUIRED)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Schema(description = "Precio del servicio",example = "12000",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer precio;

    @NotNull(message = "La duración es obligatoria")
    @Schema(description = "Duración estimada del servicio en minutos", example = "45", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer duracionMinutos;

}
