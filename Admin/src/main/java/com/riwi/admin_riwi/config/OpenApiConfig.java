package com.riwi.admin_riwi.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Riwi Mindset", version = "S.0", description = "Esta api fue creada para la logica de negocio la cual es una plataforma para el manejo de diferentes CRUDS"))
public class OpenApiConfig {

}
