package com.springboot.clienteapp.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.clienteapp.models.entity.Inmueble;
import com.springboot.clienteapp.models.entity.InmuebleCantidadSectorDTO;
import com.springboot.clienteapp.models.service.IInmuebleService;
import com.springboot.clienteapp.models.service.IPublicacionService;



@RestController
@RequestMapping("/APIanalisis")
public class ApiRestController {


	@Autowired
	private IPublicacionService publicacionService;
	
	@Autowired 
	private IInmuebleService inmuebleService; //importamos un objeto IInmuebleService
	
	
		
	@GetMapping("/apiInmuebles")
	public ResponseEntity<List<Inmueble>> apibarGraph() {
	    List<Inmueble> listadoInmuebles = inmuebleService.listarTodosInm();
	    return ResponseEntity.ok(listadoInmuebles);
	}
	
	@GetMapping("/cantidadInmueblesPorSector")
    public ResponseEntity<List<InmuebleCantidadSectorDTO>> obtenerCantidadInmueblesPorSector() {
        List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector("casa", 200000, 300000);

        List<InmuebleCantidadSectorDTO> listaSectores = new ArrayList<>();

        for (Object[] result : results) {
            String sector = (String) result[0];
            BigInteger cantidad = (BigInteger) result[1];

            InmuebleCantidadSectorDTO inmuebleCantidadSectorDTO = new InmuebleCantidadSectorDTO(sector, cantidad);
            listaSectores.add(inmuebleCantidadSectorDTO);
        }

        return ResponseEntity.ok(listaSectores);
    }

    @GetMapping("/barGraph")
    public ResponseEntity<Map<String, BigInteger>> barGraph() {
        List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector("casa", 200000, 300000);

        Map<String, BigInteger> surveyMap = new LinkedHashMap<>();

        for (Object[] result : results) {
            String sector = (String) result[0];
            BigInteger cantidad = (BigInteger) result[1];
            surveyMap.put(sector, cantidad);
        }

        return ResponseEntity.ok(surveyMap);
    }

    @GetMapping("/barGraph/{tipo}/{valor_min}/{valor_max}")
    public ResponseEntity<Map<String, BigInteger>> barGraph(
            @PathVariable("tipo") String tipo,
            @PathVariable("valor_min") float valor_min,
            @PathVariable("valor_max") float valor_max
    ) {
        List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector(tipo, valor_min, valor_max);

        Map<String, BigInteger> surveyMap = new LinkedHashMap<>();

        for (Object[] result : results) {
            String sector = (String) result[0];
            BigInteger cantidad = (BigInteger) result[1];
            surveyMap.put(sector, cantidad);
        }

        return ResponseEntity.ok(surveyMap);
    }

    @GetMapping("/displayPieChart/{tipo}/{valor_min}/{valor_max}")
    public ResponseEntity<Map<String, BigInteger>> pieChart(
            @PathVariable("tipo") String tipo,
            @PathVariable("valor_min") float valor_min,
            @PathVariable("valor_max") float valor_max
    ) {
        List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector(tipo, valor_min, valor_max);

        Map<String, BigInteger> surveyMap = new LinkedHashMap<>();

        for (Object[] result : results) {
            String sector = (String) result[0];
            BigInteger cantidad = (BigInteger) result[1];
            surveyMap.put(sector, cantidad);
        }

        return ResponseEntity.ok(surveyMap);
    }

    @GetMapping("/displayPieChartVacio")
    public ResponseEntity<Map<String, BigInteger>> pieChartVacio() {
        List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector("Apartamento", 100000, 300000);

        Map<String, BigInteger> surveyMap = new LinkedHashMap<>();

        for (Object[] result : results) {
            String sector = (String) result[0];
            BigInteger cantidad = (BigInteger) result[1];
            surveyMap.put(sector, cantidad);
        }

        return ResponseEntity.ok(surveyMap);
    }

    @GetMapping("/displayBasicAreaVacio")
    public ResponseEntity<Object> basicAreaVacio() {
        return ResponseEntity.ok().build();
    }

}