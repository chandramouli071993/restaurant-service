package com.jet.restaurants.service.restaurants.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()).info(new Info().title("Restaurant Service API")
                .description("This microservice handles event received from the external application for managing the status of "
                        + "the restaurant, it also exposes few search functionalities for the search of the restaurants "));
    }
}
