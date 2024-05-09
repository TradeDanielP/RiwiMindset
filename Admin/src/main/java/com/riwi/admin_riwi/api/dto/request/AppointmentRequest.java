package com.riwi.admin_riwi.api.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    
    @NotBlank(message = "El nombre del coder es requerido")
    private String coderName;
    @NotBlank(message = "La hora de inicio de la cita es requerida")
    private String start;
    @NotBlank(message = "La hora de final de la cita es requerida")
    private String end;
    @NotBlank(message = "El motivo de la cita es requerida")
    private String reason;
    @FutureOrPresent
    @NotBlank(message = "La fecha de la cita es requerida")
    private LocalDate date;
    @NotBlank(message = "La hora de la cita es requerida")
    private LocalTime time;
    @NotNull(message = "El id del coder es obligatorio")
    @Min(value = 1)
    private Integer coder;
    @NotNull(message = "El id de la psicologa es obligatorio")
    @Min(value = 1)
    private Integer pyschologist;
}
