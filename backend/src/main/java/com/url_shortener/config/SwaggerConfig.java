package com.url_shortener.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Encurtador URLs")
                        .description("API REST para encurtamento e redirecionamento de URLs com Cassandra e Redis")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Marcello")
                                .email("marcello.jsilva1001@gmail.com")));
    }
}
