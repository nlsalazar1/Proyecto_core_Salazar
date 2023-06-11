package com.springboot.clienteapp.models.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.clienteapp.models.entity.Cliente;
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
	
	//--------------------------------------------------------------------------
	
	@Override
	public List<Inmueble> listarInmueblesPorClienteId(Long clientes_Id) {
	    List<Inmueble> misInmuebles = (List<Inmueble>) inmuebleRepository.findAll();
	    List<Inmueble> inmueblesFiltrados = new ArrayList<>();
	    
	    Cliente cl = new Cliente();
	    
	    for (Inmueble inmueble : misInmuebles) {
	    	cl = inmueble.getCliente();
	        if (cl.getId().equals(clientes_Id)) {
	            inmueblesFiltrados.add(inmueble);
	        }
	    }
	    
	    System.out.print(inmueblesFiltrados.toString());
	    return inmueblesFiltrados;
	}

}
