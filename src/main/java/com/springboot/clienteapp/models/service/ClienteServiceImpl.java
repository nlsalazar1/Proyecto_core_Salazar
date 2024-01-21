package com.springboot.clienteapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.clienteapp.models.entity.Cliente;
import com.springboot.clienteapp.models.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository; //Indicamos que es una inyeccion de dependencia
	
	//@Override
	public List<Cliente> listarTodos() {		
		return (List<Cliente>) clienteRepository.findAll();
	}

	//@Override
	public void guardar(Cliente cliente) {

		clienteRepository.save(cliente);
	}

	//@Override
	public Cliente buscarPorId(Long id) {
		return clienteRepository.findById(id).orElse(null); //Controlamos con orElse que no nos retorne un ERROR en caso de que no exista
	}

	//@Override
	public void eliminar(Long id) {
		clienteRepository.deleteById(id); 

	}

}
