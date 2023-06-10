package com.springboot.clienteapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.clienteapp.models.entity.Ciudad;
import com.springboot.clienteapp.models.repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements ICiudadService {

	@Autowired
	private CiudadRepository ciudadRepository;
	
	//@Override
	public List<Ciudad> listaCiudades() {

		return (List<Ciudad>) ciudadRepository.findAll();
	}

	//@Override
	public void guardar(Ciudad ciudad) {

		ciudadRepository.save(ciudad);
	}

	//@Override
	public Ciudad buscarPorId(Long id) {
		return ciudadRepository.findById(id).orElse(null); //Controlamos con orElse que no nos retorne un ERROR en caso de que no exista
	}

	//@Override
	public void eliminar(Long id) {
		ciudadRepository.deleteById(id); 
	}

}
