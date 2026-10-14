package pe.com.empresa.ms_operaciones.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI operacionesOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("MS Operaciones API")
                        .description("""
                                API REST del microservicio de operaciones.

                                Microservicio desarrollado con Java 21,
                                Spring Boot y Arquitectura Hexagonal.
                                """)
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Equipo Backend")));
    }
}
