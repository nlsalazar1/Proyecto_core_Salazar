package com.springboot.clienteapp.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import java.util.LinkedHashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.clienteapp.models.entity.Cliente;
import com.springboot.clienteapp.models.entity.Inmueble;
import com.springboot.clienteapp.models.entity.InmuebleCantidadSectorDTO;
import com.springboot.clienteapp.models.service.IInmuebleService;
import com.springboot.clienteapp.models.service.IPublicacionService;

@Controller
@RequestMapping("/views/analisis")
public class AnalisisController {


	@Autowired
	private IPublicacionService publicacionService;
	
	@GetMapping("/")   // funciona envia los datos directo al html
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
    }
	
	
	@GetMapping("/mostrardisplayBarGraph")    //funciona
	public String barGraph(Model model) {
		
		List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector("casa", 200000, 300000);
        
		List<InmuebleCantidadSectorDTO> listaSectores = new ArrayList<>();

		Map<String, BigInteger> surveyMap = new LinkedHashMap<>();

		for (Object[] result : results) {
            String sector = (String) result[0];
            BigInteger cantidad = (BigInteger) result[1];
            
            
            surveyMap.put(sector, cantidad);
            //InmuebleCantidadSectorDTO inmuebleCantidadSectorDTO = new InmuebleCantidadSectorDTO(sector, cantidad);
            //listaSectores.add(inmuebleCantidadSectorDTO);
        }
		
		model.addAttribute("surveyMap", surveyMap);
		
		return "/views/analisis/AnalisisGraficas";
	}
	
	
	
	/*@PostMapping("/displayBarGraph")
	public String barGraph(@ModelAttribute("tipo") String tipo,
            @ModelAttribute("valor_min") float valor_min,
            @ModelAttribute("valor_max") float valor_max,
            Model model) {
	    List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector(tipo, valor_min, valor_max);

	    List<InmuebleCantidadSectorDTO> listaSectores = new ArrayList<>();
	    Map<String, BigInteger> surveyMap = new LinkedHashMap<>();

	    for (Object[] result : results) {
	        String sector = (String) result[0];
	        BigInteger cantidad = (BigInteger) result[1];
	        surveyMap.put(sector, cantidad);
	        //InmuebleCantidadSectorDTO inmuebleCantidadSectorDTO = new InmuebleCantidadSectorDTO(sector, cantidad);
	        //listaSectores.add(inmuebleCantidadSectorDTO);
	    }

	    model.addAttribute("surveyMap", surveyMap);

	    return "/views/analisis/AnalisisGraficas";
	}*/

	
	
	
	
	
	
	@GetMapping("/displayBarGraph/{tipo}/{valor_min}/{valor_max}")
	public String barGraph(@PathVariable("tipo") String tipo, @PathVariable("valor_min") float valor_min, @PathVariable("valor_max") float valor_max, Model model) {
	    List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector(tipo, valor_min, valor_max);

	    List<InmuebleCantidadSectorDTO> listaSectores = new ArrayList<>();
	    Map<String, BigInteger> surveyMap = new LinkedHashMap<>();

	    for (Object[] result : results) {
	        String sector = (String) result[0];
	        BigInteger cantidad = (BigInteger) result[1];
	        surveyMap.put(sector, cantidad);
	        //InmuebleCantidadSectorDTO inmuebleCantidadSectorDTO = new InmuebleCantidadSectorDTO(sector, cantidad);
	        //listaSectores.add(inmuebleCantidadSectorDTO);
	    }

	    model.addAttribute("surveyMap", surveyMap);

	    return "/views/analisis/AnalisisGraficas";
	}

	
		
	
	
	

	@GetMapping("/displayPieChart")
	public String pieChart(Model model) {
		model.addAttribute("pass", 20);
		model.addAttribute("fail", 80);
		return "/views/analisis/Grafico2";
	}
	
	
}