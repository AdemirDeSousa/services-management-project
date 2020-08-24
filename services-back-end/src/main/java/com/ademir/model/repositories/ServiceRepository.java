package com.ademir.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ademir.model.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

}
