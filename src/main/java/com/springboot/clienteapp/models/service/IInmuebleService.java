package com.springboot.clienteapp.models.service;

import java.util.List;

import com.springboot.clienteapp.models.entity.Inmueble;

public interface IInmuebleService {

	public List<Inmueble> listarTodosInm();
	
	public void guardar(Inmueble inmueble);
	
	public Inmueble buscarPorId(Integer idInmueble);
	
	public void eliminar(Integer idInmueble);
	
	
	public List<Inmueble> listarInmueblesPorClienteId(Long clientes_Id);
	
	public List<Inmueble> listarInmueblesPorMetro(String tipo, String sector, float metro);
}
