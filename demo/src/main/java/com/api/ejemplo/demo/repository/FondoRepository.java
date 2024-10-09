package com.api.ejemplo.demo.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.api.ejemplo.demo.model.Fondo;
import java.util.Optional;

@EnableScan
public interface FondoRepository  extends CrudRepository<Fondo , String> {

    Optional<Fondo> findByNombreFondo(String nombreFondo);
    
}