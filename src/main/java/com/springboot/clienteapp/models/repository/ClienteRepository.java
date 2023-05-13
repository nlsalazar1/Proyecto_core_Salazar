package com.springboot.clienteapp.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.clienteapp.models.entity.Cliente;

@Repository //Indicamos que esta clase es un repository, aunque actualmente ya no es necesario indicarlo
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	
}
