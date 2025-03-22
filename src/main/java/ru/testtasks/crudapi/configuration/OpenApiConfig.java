package ru.testtasks.crudapi.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Value("${spring.application.name}")
  private String applicationName;

  @Bean
  public OpenAPI customOpenAPI(
  ) {
    return new OpenAPI()
        .components(new Components().addSecuritySchemes("iam authentification",
            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization")))
        .info(
            new Info()
                .title(applicationName)
                .description(applicationName))
        .addSecurityItem(
            new SecurityRequirement().addList("iam authentification",
                Arrays.asList("read", "write"))
        );
  }

}
