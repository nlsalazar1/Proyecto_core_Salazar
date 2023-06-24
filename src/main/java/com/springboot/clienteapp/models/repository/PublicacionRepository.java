package com.springboot.clienteapp.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.clienteapp.models.entity.Publicacion;

@Repository
public interface PublicacionRepository extends CrudRepository<Publicacion, Integer> {

}
