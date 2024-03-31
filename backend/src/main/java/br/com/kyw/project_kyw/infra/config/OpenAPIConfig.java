package br.com.kyw.project_kyw.infra.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer ",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPi() {
        return new OpenAPI()
                .info(new Info()
                        .title("KYW Project")
                        .version("v1")
                        .description("Project for task management \"Integrator Project,\" allows separation by projects and tasks")
                        .termsOfService("https://www.kyw.com.br/termsOfServices")
                        .license(new License().name("Apache 2.0")
                                .url("https://www.kyw.com.br")));

    }

}