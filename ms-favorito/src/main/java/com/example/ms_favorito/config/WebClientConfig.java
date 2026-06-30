package com.example.ms_favorito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient productosWebClient() {

        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }
}