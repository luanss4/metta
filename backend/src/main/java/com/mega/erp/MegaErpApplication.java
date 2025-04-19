package com.mega.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MegaErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MegaErpApplication.class, args);
    }
}