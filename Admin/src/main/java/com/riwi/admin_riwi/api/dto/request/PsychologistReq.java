package com.riwi.admin_riwi.api.dto.request;

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
public class PsychologistReq {
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String name;
    @Email
    private String email;
    @NotNull(message = "la cedula es requerida")
    @Min(value = 1)
    private String document;
    @NotBlank(message = "contraseña requerida")
    private String password;
    @NotBlank(message = "la foto es obligatoria")
    private String photo;
    @NotNull(message = "el role es obligatorio")
    private String role;


}
