package com.ademir.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ademir.model.entities.Client;
import com.ademir.model.repositories.ClientRepository;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Client save(@RequestBody @Valid Client client) {
		return clientRepository.save(client);
	}
	
	@GetMapping("/{id}")
	public Client findById(@PathVariable Integer id) {
		return clientRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		clientRepository.findById(id)
			.map(client -> {
				clientRepository.delete(client);
				return Void.TYPE;
			})
			.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Client clientUpdated) {
		clientRepository.findById(id)
			.map(client -> {
				clientUpdated.setId(client.getId());
				return clientRepository.save(clientUpdated);
			})
			.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
	}
}
