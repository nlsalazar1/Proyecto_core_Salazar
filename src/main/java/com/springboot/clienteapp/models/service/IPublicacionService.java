package com.springboot.clienteapp.models.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.springboot.clienteapp.models.entity.Publicacion;

@Transactional
public interface IPublicacionService {

	public List<Publicacion> listarTodosPub();
	
	public void guardar(Publicacion publicacion);
	
	public Publicacion buscarPorId(Integer id_Publicacion);
	
	public void eliminar(Integer id_Publicacion);
	
	public List<Publicacion> listarPublicacionesPorInmuebleId(Integer inmuebles_Id);
	
	public List<Object[]> obtenerCantidadInmueblesConSector(String tipo, float precioMin, float precioMax);
	
	public List<Object[]> ObtenerInmueblesSimilares(String tipo, int banios, int dormitorios, int area);
}
