package com.springboot.clienteapp.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.clienteapp.models.entity.Inmueble;

@Repository
public interface InmuebleRepository extends CrudRepository<Inmueble, Integer> {


	/*@Query("SELECT i FROM Inmueble i WHERE i.cliente.id = :clienteId")
    List<Inmueble> findAllByClienteId(@Param("clienteId") Integer clienteId);
	*/
}
