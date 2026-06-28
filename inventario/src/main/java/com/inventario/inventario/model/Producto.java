package com.inventario.inventario.model;

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
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo que representa un producto disponible en el inventario")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único autoincremental del producto", example = "1")
    private Long idProducto;

    @NotBlank(message = "El código es obligatorio")
    @Size(min = 2, max = 50, message = "El código debe tener entre 2 y 50 caracteres")
    @Schema(description = "Código identificador del producto", example = "PROD-001",requiredMode = Schema.RequiredMode.REQUIRED)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema( description = "Nombre del producto", example = "Cera para cabello",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "La marca es obligatoria")
    @Size(min = 2, max = 100, message = "La marca debe tener entre 2 y 100 caracteres")
    @Schema(description = "Marca del producto",example = "L'Oréal", requiredMode = Schema.RequiredMode.REQUIRED)
    private String marca;

    @NotNull(message = "El precio es obligatorio")
    @Schema( description = "Precio del producto", example = "8500", requiredMode = Schema.RequiredMode.REQUIRED)
    private int precio;

    @NotNull(message = "La cantidad es obligatoria")
    @Schema(description = "Cantidad disponible en inventario", example = "25",requiredMode = Schema.RequiredMode.REQUIRED)
    private int cantidad;


}
