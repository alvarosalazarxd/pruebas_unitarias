package com.example.ms_favorito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.ms_favorito") 
public class MsFavoritoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsFavoritoApplication.class, args);
    }
}