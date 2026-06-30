package com.envios.ms_envios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.envios.ms_envios")
public class MsEnviosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsEnviosApplication.class, args);
    }
}