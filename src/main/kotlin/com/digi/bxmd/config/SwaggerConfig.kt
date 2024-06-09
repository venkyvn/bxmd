package com.digi.bxmd.config
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("Spring Boot REST API")
                    .description("Spring Boot REST API documentation")
                    .version("v1.0.0")
                    .contact(
                        Contact().name("Your Name")
                            .url("https://www.yourwebsite.com")
                            .email("your-email@yourwebsite.com")
                    )
                    .license(
                        License().name("Apache 2.0")
                            .url("http://springdoc.org")
                    )
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("Spring Boot REST API Documentation")
                    .url("https://www.yourwebsite.com/docs")
            )
    }

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("public-api")
            .pathsToMatch("/**")
            .build()
    }
}
