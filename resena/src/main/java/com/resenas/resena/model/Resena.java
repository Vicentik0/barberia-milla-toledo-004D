package com.resenas.resena.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Cantidad de estrellas de 1 a 5", example = "5")
    private Integer estrellas;

    @Schema(description = "Comentario del cliente", example = "Excelente servicio, muy detallista")
    private String comentario;

    @Schema(description = "ID del barbero calificado", example = "1")
    private Long barberoId; 

    @Transient 
    @Schema(description = "Datos del barbero cargados vía WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    private Object datosBarbero; 
}
