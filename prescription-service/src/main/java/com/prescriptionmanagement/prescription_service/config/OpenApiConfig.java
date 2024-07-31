package com.prescriptionmanagement.prescription_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Configuration class named {@link OpenApiConfig} for OpenAPI documentation.
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Osman ÇANKAYA",
                        url = "https://github.com/ocankaya/prescription-micro-service", //TODO: github url'si gir.
                        email = "osmancankayabilmuh@gmail.com"
                ),
                description = "Prescription Management - Microservice" +
                        "(Spring boot, Rest Api, hashiCorp Vault (centralized configuration), " +
                        "Eureka Server, Resilience4j, Zipkin, Feign Client, Docker, Actuator, " +
                        "Open Api, Lombok, Mapstruct)", //TODO: teknolojileri kontrol et
                title = "Prescription Management Online App",
                version = "1.0.0"
        ) //end of info
)
public class OpenApiConfig {
}