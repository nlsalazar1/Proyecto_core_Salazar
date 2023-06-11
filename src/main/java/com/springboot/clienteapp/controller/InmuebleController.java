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

import com.springboot.clienteapp.models.entity.Cliente;
import com.springboot.clienteapp.models.entity.Inmueble;
import com.springboot.clienteapp.models.service.IClienteService;
import com.springboot.clienteapp.models.service.IInmuebleService;

@Controller
@RequestMapping("/views/inmuebles")
public class InmuebleController {

	@Autowired 
	private IInmuebleService inmuebleService; //importamos un objeto IInmuebleService
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/")
	public String listarInmuebles(Model model) {
		
		List<Inmueble> listadoInmuebles = inmuebleService.listarTodosInm(); //Usamos un metodo de la clase que estan en IInmuebleService
		
		model.addAttribute("titulo","Lista de Inmuebles"); //Podemos enviar el titulo de la pagina desde esta clase
		model.addAttribute("inmuebles", listadoInmuebles); //Enviamos a la pag el listado de clientes
		return "/views/inmuebles/listarinm";
	}
	
	@GetMapping("/create")
	public String crear(Model model){
		
		Inmueble inmueble = new Inmueble();
		List<Cliente> listClientes = clienteService.listarTodos();
		
		model.addAttribute("titulo", "Formulario: Nuevo Inmueble"); //Enviamos informacion al formulario
		model.addAttribute("inmueble", inmueble);
		model.addAttribute("clientes", listClientes);
		
		return "/views/inmuebles/frmCrearinm";
	}
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Inmueble inmueble, BindingResult result, Model model) { //@BindigResult captura los errores del formulario 
		
		List<Cliente> listClientes = clienteService.listarTodos();
		
		if(result.hasErrors()) {
			
			
			model.addAttribute("titulo", "Formulario: Nuevo Inmueble"); //Enviamos informacion al formulario
			model.addAttribute("inmueble", inmueble);
			model.addAttribute("clientes", listClientes);
			System.out.println("Existieron errores en el formulario..!!!");
			return "/views/inmuebles/frmCrear";
		}
		
		inmuebleService.guardar(inmueble);
		
		System.out.println("inmueble guardado con exito..!!!");
		return "redirect:/views/inmuebles/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer idInmueble, Model model){
		
		Inmueble inmueble = inmuebleService.buscarPorId(idInmueble);
		List<Cliente> listClientes = clienteService.listarTodos();
		
		model.addAttribute("titulo", "Formulario: Editar Inmueble"); //Enviamos informacion al formulario
		model.addAttribute("inmueble", inmueble);
		model.addAttribute("clientes", listClientes);
		
		return "/views/inmuebles/frmCrearinm";
	}
	
	@GetMapping("/delete/{idInmueble}")
	public String eliminar(@PathVariable("idInmueble") Integer idInmueble){
		
		inmuebleService.eliminar(idInmueble);
		System.out.println("El registro se elimino con exito...!!!");
		
		return "/views/inmuebles/";
	}	
	
	
	
}
	
	/*@GetMapping("/")
	public String list(Model model) {
		
		List<Inmueble> listadoInmuebles = inmuebleService.list(); //Usamos un metodo de la clase que estan en IInmuebleService
		
		model.addAttribute("titulo","Lista de Inmuebles");
        model.addAttribute("inmuebles", listadoInmuebles);
        return "/views/inmuebles/list";
    }*/

