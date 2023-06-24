package com.springboot.clienteapp.models.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.math.BigInteger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.clienteapp.models.entity.InmuebleCantidadSectorDTO;
import com.springboot.clienteapp.models.entity.Publicacion;
import com.springboot.clienteapp.models.repository.InmuebleRepository;
import com.springboot.clienteapp.models.repository.PublicacionRepository;

@Service
@Transactional
public class IpublicacionServiceImpl implements IPublicacionService {

	@Autowired
	private PublicacionRepository publicacionRepository;
	
	@Autowired
	private InmuebleRepository inmuebleRepository;
	
	@Override
	public List<Publicacion> listarTodosPub() {
		
		return (List<Publicacion>) publicacionRepository.findAll();
	}

	@Override
	public void guardar(Publicacion publicacion) {
		publicacionRepository.save(publicacion);
	}

	@Override
	public Publicacion buscarPorId(Integer id_Publicacion) {

		return publicacionRepository.findById(id_Publicacion).orElse(null);
	}

	@Override
	public void eliminar(Integer id_Publicacion) {

		publicacionRepository.deleteById(id_Publicacion);
	}

	@Override
	public List<Publicacion> listarPublicacionesPorInmuebleId(Integer inmuebles_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Object[]> obtenerCantidadInmueblesConSector(String tipo, float precioMin, float precioMax) {
        
		List<Object[]> results = inmuebleRepository.obtenerCantidadInmueblesConSector(tipo, precioMin, precioMax);
		
		/*List<InmuebleCantidadSectorDTO> listaSectores = new ArrayList<>();

		for (Object[] result : results) {
		    String sector = (String) result[0];
		    BigInteger cantidad = (BigInteger) result[1];
		    InmuebleCantidadSectorDTO inmuebleCantidadSectorDTO = new InmuebleCantidadSectorDTO(sector, cantidad);
		    listaSectores.add(inmuebleCantidadSectorDTO);
		}

		for (InmuebleCantidadSectorDTO inmueble : listaSectores) {
		    System.out.println("Sector: " + inmueble.getSector());
		    System.out.println("Cantidad: " + inmueble.getCantidad());
		}*/
		return results;
        
    }
	


	
	

}
