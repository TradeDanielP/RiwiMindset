package com.riwi.test.api.dto.request;

import com.riwi.test.domain.entities.TestEntity;

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
public class TestScoreResq {
    @NotBlank(message = "El id del coder es requerido")
    public String idCoder;
    @NotBlank(message = "El score del coder es requerido")
    public String score;
    @NotNull(message = "El id del test del coder es requerido")
    public TestEntity test;
}
