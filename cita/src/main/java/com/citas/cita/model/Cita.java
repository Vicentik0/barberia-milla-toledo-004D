package com.citas.cita.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo que representa una cita agendada en la barbería")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(description = "Identificador único autoincremental de la cita", example = "1")
    private Long IdCita;

    @NotNull(message = "Debe indicar un cliente")
    @Schema(description = "Identificador del cliente asociado a la cita",example = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long IdCliente;



    @NotNull(message = "Debe indicar un barbero")
    @Schema(description = "Identificador del barbero asignado a la cita",example = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long IdBarbero;


    @NotNull(message = "Debe indicar un producto")
    @Schema(description = "Identificador del producto asociado a la cita",example = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long IdSucursal;



    @NotNull(message = "Debe indicar un producto")
    @Schema(description = "Identificador del producto asociado a la cita",example = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long IdProducto;



    @NotBlank(message = "El servicio es obligatorio")
    @Size(min = 3, max = 100, message = "El servicio debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre del servicio solicitado",example = "Corte de cabello", requiredMode = Schema.RequiredMode.REQUIRED)
    private String servicio;


    @NotNull(message = "La fecha es obligatoria")
    @Schema(description = "Fecha programada para la cita",example = "2026-06-30",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fecha;



    @NotBlank(message = "El estado es obligatorio")
    @Size(min = 3, max = 30, message = "El estado debe tener entre 3 y 30 caracteres")
    @Schema( description = "Estado actual de la cita",example = "Agendada",requiredMode = Schema.RequiredMode.REQUIRED)
    private String estado;

}
