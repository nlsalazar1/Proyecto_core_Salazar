package com.springboot.clienteapp.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.clienteapp.models.entity.Inmueble;

@Repository
public interface InmuebleRepository extends CrudRepository<Inmueble, Integer> {

}
