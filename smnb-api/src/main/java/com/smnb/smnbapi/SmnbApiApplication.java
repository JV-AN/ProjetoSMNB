package com.smnb.smnbapi;

import com.smnb.smnbapi.model.Pacient;
import com.smnb.smnbapi.repository.PacientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

@SpringBootApplication
public class SmnbApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmnbApiApplication.class, args);
	}
	@Bean
	CommandLineRunner init(PacientRepository repository) {
		return args -> {
			repository.deleteAll();
		};
	}
}
