package com.springboot.clienteapp.controller;

import java.util.List;
import java.math.BigInteger;
import java.util.ArrayList;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.clienteapp.models.entity.Ciudad;
import com.springboot.clienteapp.models.entity.Cliente;
import com.springboot.clienteapp.models.entity.Inmueble;
import com.springboot.clienteapp.models.entity.InmuebleCantidadSectorDTO;
import com.springboot.clienteapp.models.service.ICiudadService;
import com.springboot.clienteapp.models.service.IClienteService;
import com.springboot.clienteapp.models.service.IInmuebleService;
import com.springboot.clienteapp.models.service.IPublicacionService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {
	
	@Autowired 
	private IClienteService clienteService; //importamos un objeto IClienteService
	
	@Autowired
	private ICiudadService ciudadService;
	
	@Autowired 
	private IInmuebleService inmuebleService; //importamos un objeto IInmuebleService
	

	@Autowired
	private IPublicacionService publicacionService;
	
	
	//@Secured({"ROLE_ADMIN", "ROLE_USER"}) //video 12 seguridad - hay que avilitar la anotacion en WebSecurityConfig
	@GetMapping("/")
	public String listarClientes(Model model) {
		
		List<Cliente> listadoClientes = clienteService.listarTodos(); //Usamos un metodo de la clase que estan en IClienteService
		
		model.addAttribute("titulo","Lista de Clientes"); //Podemos enviar el titulo de la pagina desde esta clase
		model.addAttribute("clientes", listadoClientes); //Enviamos a la pag el listado de clientes
		return "/views/clientes/listar";
	}
	
	//@Secured("ROLE_ADMIN") //video 12 seguridad
	@GetMapping("/create") 
	public String crear(Model model){
		
		Cliente cliente = new Cliente();
		List<Ciudad> listCiudades = ciudadService.listaCiudades();
		
		model.addAttribute("titulo", "Formulario: Nuevo Cliente"); //Enviamos informacion al formulario
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);
		
		return "/views/clientes/frmCrear";
	}
	
	//@Secured("ROLE_ADMIN") //video 12 seguridad
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result, //@BindigResult captura los errores del formulario 
			Model model, RedirectAttributes attribute) { //RedirectAttributes utilizamos para dar los mensajes de exito o error de la plantilla - video 14 crud
		
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
		attribute.addFlashAttribute("success","Cliente guardado con exito..!!!");
		return "redirect:/views/clientes/";
	}
	
	//@Secured("ROLE_ADMIN") //video 12 seguridad
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idCliente, Model model, RedirectAttributes attribute){
		
		Cliente cliente = null;
		
		if(idCliente > 0) { //video 13 crud asegurar de que exista el cliente
			cliente = clienteService.buscarPorId(idCliente);
			if(cliente == null) {
				System.out.println("Error: El ID del cliente no existe!");
				attribute.addFlashAttribute("error","ATENCIÓN: Error el ID del cliente no existe!!!");

				return "redirect:/views/clientes/";
			}
		}else {
			System.out.println("Error: Error con el ID del cliente!");
			attribute.addFlashAttribute("error","ATENCIÓN: Error con el ID del cliente!!!");

			return "redirect:/views/clientes/";
		}
		
		List<Ciudad> listCiudades = ciudadService.listaCiudades();
		
		model.addAttribute("titulo", "Formulario: Editar Cliente"); //Enviamos informacion al formulario
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);
		
		return "/views/clientes/frmCrear";
	}
	
	//@Secured("ROLE_ADMIN") //video 12 seguridad
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idCliente, RedirectAttributes attribute){
		
		Cliente cliente = null;
		
		if(idCliente>0) {
			cliente = clienteService.buscarPorId(idCliente);
			
			if(cliente == null) {
				System.out.println("Error: El ID del cliente no existe!");
				attribute.addFlashAttribute("error","ATENCIÓN: Error el ID del cliente no existe!!!");

				return "redirect:/views/clientes/";
			}
		}else {
			System.out.println("Error: Error con el ID del cliente!");
			attribute.addFlashAttribute("error","ATENCIÓN: Error con el ID del cliente!!!");

			return "redirect:/views/clientes/";
		}				
			
		
		clienteService.eliminar(idCliente);
		System.out.println("El registro se elimino con exito...!!!");
		attribute.addFlashAttribute("warning","ATENCIÓN: El registro se elimino con exito...!!!");

		return "redirect:/views/clientes/";
	}
	
	
	//-------------------------------------------------------------------------------------
	
	//@Secured({"ROLE_ADMIN", "ROLE_USER"}) //video 12 seguridad
	@GetMapping("/inmueblesCli/{clientes_Id}")
	public String listarInmueblesPorCliente(@PathVariable("clientes_Id") Long clientes_Id, Model model) {
	    
		List<Inmueble> listadoInmuebles = inmuebleService.listarInmueblesPorClienteId(clientes_Id);
	    model.addAttribute("titulo", "Lista de Inmuebles del Cliente");
	    model.addAttribute("inmuebles", listadoInmuebles);

	    return "views/inmuebles/listaInmCliente";
	}
	
	
	//@Secured({"ROLE_ADMIN", "ROLE_USER"}) //video 12 seguridad
	@GetMapping("/inmueblesCliImg/{clientes_Id}")
	public String mostrarInmueblesPorCliente(@PathVariable("clientes_Id") Long clientes_Id, Model model) {
	    
		publicacionService.obtenerCantidadInmueblesConSector("casa", 200000, 300000);

		List<Inmueble> listadoInmuebles = inmuebleService.listarInmueblesPorClienteId(clientes_Id);
	    model.addAttribute("titulo", "Lista de Inmuebles del Cliente");
	    model.addAttribute("inmuebles", listadoInmuebles);

	    return "views/inmuebles/mostrarInmuebles";
	}
	
	
	/*@GetMapping("/sectores")   // funciona envia los datos directo al html
    public String obtenerCantidadInmueblesPorSector(Model model) {
        // Lógica para obtener los datos de cantidad de inmuebles por sector desde el repositorio
        
		List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector("casa", 200000, 300000);
        
		List<InmuebleCantidadSectorDTO> listaSectores = new ArrayList<>();

        for (Object[] result : results) {
            String sector = (String) result[0];
            BigInteger cantidad = (BigInteger) result[1];
            
            InmuebleCantidadSectorDTO inmuebleCantidadSectorDTO = new InmuebleCantidadSectorDTO(sector, cantidad);
            listaSectores.add(inmuebleCantidadSectorDTO);
        }
        
        

		model.addAttribute("titulo","Cantidad de inmuebles por sector"); //Podemos enviar el titulo de la pagina desde esta clase
		model.addAttribute("InmuebleCantidadSectorDTO", listaSectores); //Enviamos a la pag el listado de clientes
		
		return "/views/analisis/AnalisisGraficas";
        
        
        //return listaSectores;
    }*/
	
	
	
	    /*@GetMapping("/sectores") //Enviamos los datos directamente a graficas.js
	    @ResponseBody
	    public List<InmuebleCantidadSectorDTO> obtenerCantidadInmueblesPorSector() {
	        // Lógica para obtener los datos de cantidad de inmuebles por sector desde el repositorio
	        List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector("casa", 200000, 300000);
	        List<InmuebleCantidadSectorDTO> listaSectores = new ArrayList<>();

	        for (Object[] result : results) {
	            String sector = (String) result[0];
	            BigInteger cantidad = (BigInteger) result[1];

	            InmuebleCantidadSectorDTO inmuebleCantidadSectorDTO = new InmuebleCantidadSectorDTO(sector, cantidad);
	            listaSectores.add(inmuebleCantidadSectorDTO);
	        }

	        return listaSectores;
	    }*/
	
}