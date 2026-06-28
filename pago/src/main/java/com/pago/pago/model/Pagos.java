package com.pago.pago.model;

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
@Table(name="pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo que representa un pago realizado por un cliente")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único autoincremental del pago", example = "1")
    private Long idPago;

    @NotNull(message = "Debe indicar un cliente")
    @Schema(description = "Identificador del cliente que realiza el pago",example = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idCliente;

    @NotNull(message = "Debe indicar una cita")
    @Schema( description = "Identificador de la cita asociada al pago", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idCita;

    @NotNull(message = "El monto es obligatorio")
    @Schema( description = "Monto total pagado", example = "12000",requiredMode = Schema.RequiredMode.REQUIRED)
    private Double monto;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(min = 3, max = 50, message = "El método de pago debe tener entre 3 y 50 caracteres")
    @Schema(description = "Método utilizado para realizar el pago", example = "Tarjeta de débito",requiredMode = Schema.RequiredMode.REQUIRED)
    private String metodoPago;

    @NotBlank(message = "El estado del pago es obligatorio")
    @Size(min = 3, max = 30, message = "El estado del pago debe tener entre 3 y 30 caracteres")
    @Schema(description = "Estado actual del pago", example = "Pagado",requiredMode = Schema.RequiredMode.REQUIRED)
    private String estadoPago;

}
