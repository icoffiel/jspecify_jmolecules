package com.icoffiel.jspecify;

import org.springframework.boot.SpringApplication;

public class TestJspecifyApplication {

    public static void main(String[] args) {
        SpringApplication.from(JspecifyApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
