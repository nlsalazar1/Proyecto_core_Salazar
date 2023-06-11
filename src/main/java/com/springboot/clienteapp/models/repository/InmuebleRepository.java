package com.springboot.clienteapp.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.clienteapp.models.entity.Inmueble;

@Repository
public interface InmuebleRepository extends CrudRepository<Inmueble, Integer> {
	//List<Inmueble> findByClienteId(Long clientes_Id);
}
