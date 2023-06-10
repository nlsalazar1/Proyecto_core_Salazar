package com.springboot.clienteapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.clienteapp.models.entity.Inmueble;
import com.springboot.clienteapp.models.repository.InmuebleRepository;

@Service
public class InmuebleServiceImpl implements IInmuebleService {

	@Autowired
	private InmuebleRepository inmuebleRepository;
	
	@Override
	public List<Inmueble> listarTodosInm() {
		
		return (List<Inmueble>) inmuebleRepository.findAll();
	}

	@Override
	public void guardar(Inmueble inmueble) {
		inmuebleRepository.save(inmueble);

	}

	@Override
	public Inmueble buscarPorId(Integer idInmueble) {
		
		return inmuebleRepository.findById(idInmueble).orElse(null);
	}

	@Override
	public void eliminar(Integer idInmueble) {
		inmuebleRepository.deleteById(idInmueble);

	}

}
