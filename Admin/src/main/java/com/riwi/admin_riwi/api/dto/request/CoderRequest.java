package com.riwi.admin_riwi.api.dto.request;

import java.math.BigInteger;
import java.time.LocalDate;

import com.riwi.admin_riwi.util.enums.ClanEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoderRequest {
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String name;
    private ClanEnum clan;
    @Size(min = 1, max = 21, message = "El teléfono debe tener entre 10 y 20 caracteres")
    private String phone;
    @Email(message = "debe ser formato @ email")
    private String email;
    @NotNull(message = "la fecha es obligatoria")
    private LocalDate dateborn;
    private String photo;
    @NotNull(message = "la cedula es obligatoria")
    @Min(value=1)
    private BigInteger cc;
}
