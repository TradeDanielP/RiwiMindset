package com.riwi.riwi_mindset.infraestructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**")  // Ruta API específica
                .allowedOrigins("*")  // Origen permitido (puedes configurar más de uno si es necesario)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                .allowedHeaders("*")  // Encabezados permitidos (puedes ajustar según necesidades específicas)
                .allowCredentials(true);  // Permitir credenciales si es necesario
    }
}