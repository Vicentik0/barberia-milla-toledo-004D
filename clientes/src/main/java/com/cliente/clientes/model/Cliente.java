package com.cliente.clientes.model;

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
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo que representa a un cliente de la barbería")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(description = "Identificador único autoincremental del cliente", example = "1")
    private Long IdCliente;



    @NotBlank(message = "El RUT es obligatorio")
    @Size(min = 9, max = 12, message = "El RUT debe tener entre 9 y 12 caracteres")
    @Schema(description = "RUT del cliente con guion", example = "12345678-9",requiredMode = Schema.RequiredMode.REQUIRED)
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre completo del cliente",example = "Luis enrique",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 8, max = 15, message = "El teléfono debe tener entre 8 y 15 caracteres")
    @Schema(description = "Número de teléfono de contacto del cliente",example = "+56912345678",requiredMode = Schema.RequiredMode.REQUIRED)
    private String telefono;


}
