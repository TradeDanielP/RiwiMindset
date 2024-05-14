package com.riwi.test.api.dto.request;
import java.util.List;

import com.riwi.test.utils.enums.TypeTest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestReq {
    @NotBlank( message = "El tipo es requerido es requerida")
    private TypeTest typeTest;
    @NotBlank( message = "La pregunta es requerida")
    private String question;
    @NotBlank( message = "La respuesta es requerida")
    @ElementCollection
    private List<String> answers;
    @NotNull(message = "La correcta es requerida")
    private int answerCorrectIndex;
}
