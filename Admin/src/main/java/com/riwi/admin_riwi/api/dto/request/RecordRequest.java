package com.riwi.admin_riwi.api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
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
public class RecordRequest {
    
    @FutureOrPresent(message = "La fecha y hora debe ser futura")
    @NotNull(message = "La fecha y hora de la cita es requeridas")
    private LocalDate dateRecord;
    @NotBlank(message = "El registro es requerido")
    private String registration;
    @NotBlank(message = "La observacion es requerida")
    private String observation;
    @NotBlank(message = "El id del coder es requerido")
    private String coderId;

}
