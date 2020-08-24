package com.ademir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ademir.model.entities.Client;
import com.ademir.model.repositories.ClientRepository;

@SpringBootApplication
public class ServicesBackEndApplication {
	
	@Bean
	public CommandLineRunner run( @Autowired ClientRepository clientRepository) {
		return args -> {
			Client client = Client.builder().name("Bruno Lacerda").cpf("11111111111").build();
			clientRepository.save(client);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ServicesBackEndApplication.class, args);
		
	}

}
