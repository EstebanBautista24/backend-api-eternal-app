package com.eternal.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		LocalDate fechaInicio = LocalDate.now().withDayOfMonth(1);
		LocalDate fechaFin = LocalDate.now().plusMonths(1).withDayOfMonth(1);

		System.out.println(fechaInicio + " - " + fechaFin);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
