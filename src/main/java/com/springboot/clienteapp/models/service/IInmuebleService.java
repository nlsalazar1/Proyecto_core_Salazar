package com.springboot.clienteapp.models.service;

import java.util.List;

import com.springboot.clienteapp.models.entity.Inmueble;

public interface IInmuebleService {

	public List<Inmueble> listarTodos();
	public void guardar(Inmueble inmueble);
	public Inmueble buscarPorId(Integer id);
	public void eliminar(Integer id);

	//List<Inmueble> list();
}
