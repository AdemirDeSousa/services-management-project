package com.ademir.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ademir.model.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
