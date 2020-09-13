package com.ademir.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceProvidedDTO {
	
	private String description;
	private String value;
	private String date;
	private Integer id_client;
}
