package com.springboot.clienteapp.models.service;

import java.util.List;

import com.springboot.clienteapp.models.entity.Ciudad;
import com.springboot.clienteapp.models.entity.Cliente;

public interface ICiudadService {

	public List<Ciudad> listaCiudades();
	public void guardar(Ciudad ciudad);
	public Ciudad buscarPorId(Long id);
	public void eliminar(Long id);
}
