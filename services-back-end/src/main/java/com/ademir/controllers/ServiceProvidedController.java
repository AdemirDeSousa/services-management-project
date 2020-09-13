package com.ademir.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ademir.model.dto.ServiceProvidedDTO;
import com.ademir.model.entities.Client;
import com.ademir.model.entities.ServiceProvided;
import com.ademir.model.repositories.ClientRepository;
import com.ademir.model.repositories.ServiceProvidedRepository;
import com.ademir.util.BigDecimalConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/services-provided")
@RequiredArgsConstructor
public class ServiceProvidedController {
	
	private final ClientRepository clientRepository;
	private final ServiceProvidedRepository serviceProvidedRepository;
	private final BigDecimalConverter bigDecimalConverter;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ServiceProvided save(@RequestBody ServiceProvidedDTO dto) {
		
		LocalDate date = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer id_client = dto.getId_client();
		
		Client client = clientRepository.findById(id_client)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));
		
		ServiceProvided serviceProvided = new ServiceProvided();
		serviceProvided.setDescription(dto.getDescription());
		serviceProvided.setDate(date);
		serviceProvided.setClient(client);
		serviceProvided.setValue(bigDecimalConverter.convert(dto.getValue()));
		
		return serviceProvidedRepository.save(serviceProvided);
	}
	
	@GetMapping
	public List<ServiceProvided> search(
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "month", required = false) Integer month){
		
		return serviceProvidedRepository.findByNameClientAndMonth("%" + name + "%", month);
	}
}
