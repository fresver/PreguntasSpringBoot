package com.questions.dam2.apirest.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API REST de Preguntas")
                .version("1.0.0")
                .description("Documentación de la API REST para gestionar preguntas y respuestas")
                .contact(new Contact()
                    .name("Fabio")
                    .email("fabiorv58@gmail.com")
                    .url("home")
                )
            );
    }
}