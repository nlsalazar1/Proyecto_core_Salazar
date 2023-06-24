package com.springboot.clienteapp.models.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.clienteapp.models.entity.Inmueble;
import com.springboot.clienteapp.models.entity.InmuebleCantidadSectorDTO;

@Repository
@Transactional
public interface InmuebleRepository extends CrudRepository<Inmueble, Integer> {

	 @Transactional
	 @Query(value = "CALL ObtenerCantidadInmueblesConSector(:tipo, :precioMin, :precioMax, @cantidad, @sectores);", nativeQuery = true)
	 List<Object[]> obtenerCantidadInmueblesConSector(@Param("tipo") String tipo, @Param("precioMin") float precioMin, @Param("precioMax") float precioMax);

	 @Query(value = "SELECT @cantidad AS cantidad, @sectores AS sectores;", nativeQuery = true)
	 Map<String, Object> obtenerResultadoCantidadSectores();

	    
}
