package com.api.ejemplo.demo.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.api.ejemplo.demo.model.Cliente;

@EnableScan
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	Optional<Cliente> findByNumeroIdentificacion(String numeroIdentificacion);

}
