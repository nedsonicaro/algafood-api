package com.algaworks.algafoodapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"domain.model"})
public class AlgafoodApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgafoodApiApplication.class, args);
    }
}

