package com.springboot.clienteapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.clienteapp.models.entity.Ciudad;
import com.springboot.clienteapp.models.entity.Cliente;
import com.springboot.clienteapp.models.entity.Inmueble;
import com.springboot.clienteapp.models.service.ICiudadService;
import com.springboot.clienteapp.models.service.IClienteService;
import com.springboot.clienteapp.models.service.IInmuebleService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {
	
	@Autowired 
	private IClienteService clienteService; //importamos un objeto IClienteService
	
	@Autowired
	private ICiudadService ciudadService;
	
	@Autowired 
	private IInmuebleService inmuebleService; //importamos un objeto IInmuebleService
	
	@GetMapping("/")
	public String listarClientes(Model model) {
		
		List<Cliente> listadoClientes = clienteService.listarTodos(); //Usamos un metodo de la clase que estan en IClienteService
		
		model.addAttribute("titulo","Lista de Clientes"); //Podemos enviar el titulo de la pagina desde esta clase
		model.addAttribute("clientes", listadoClientes); //Enviamos a la pag el listado de clientes
		return "/views/clientes/listar";
	}
	
	@GetMapping("/create")
	public String crear(Model model){
		
		Cliente cliente = new Cliente();
		List<Ciudad> listCiudades = ciudadService.listaCiudades();
		
		model.addAttribute("titulo", "Formulario: Nuevo Cliente"); //Enviamos informacion al formulario
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);
		
		return "/views/clientes/frmCrear";
	}
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model) { //@BindigResult captura los errores del formulario 
		
		List<Ciudad> listCiudades = ciudadService.listaCiudades();
		
		if(result.hasErrors()) {
			
			
			model.addAttribute("titulo", "Formulario: Nuevo Cliente"); //Enviamos informacion al formulario
			model.addAttribute("cliente", cliente);
			model.addAttribute("ciudades", listCiudades);
			System.out.println("Existieron errores en el formulario..!!!");
			return "/views/clientes/frmCrear";
		}
		
		clienteService.guardar(cliente);
		
		System.out.println("Cliente guardado con exito..!!!");
		return "redirect:/views/clientes/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idCliente, Model model){
		
		Cliente cliente = clienteService.buscarPorId(idCliente);
		List<Ciudad> listCiudades = ciudadService.listaCiudades();
		
		model.addAttribute("titulo", "Formulario: Editar Cliente"); //Enviamos informacion al formulario
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);
		
		return "/views/clientes/frmCrear";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idCliente){
		
		clienteService.eliminar(idCliente);
		System.out.println("El registro se elimino con exito...!!!");
		
		return "redirect:/views/clientes/";
	}
	
	
	//-------------------------------------------------------------------------------------
	
	@GetMapping("/inmueblesCli/{clientes_Id}")
	public String listarInmueblesPorCliente(@PathVariable("clientes_Id") Long clientes_Id, Model model) {
	    
		List<Inmueble> listadoInmuebles = inmuebleService.listarInmueblesPorClienteId(clientes_Id);
	    model.addAttribute("titulo", "Lista de Inmuebles del Cliente");
	    model.addAttribute("inmuebles", listadoInmuebles);

	    return "views/inmuebles/listaInmCliente";
	}
}