package com.riwi.admin_riwi.api.dto.request;

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
public class RecordBasicResponse {
    @NotBlank(message = "El registro es requerido")
    private String registration;
    @NotBlank(message = "La observacion es requerida")
    private String observation;

}
