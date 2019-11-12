package com.micromax.incidencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IncidenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidenciaApplication.class, args);


	}
}
