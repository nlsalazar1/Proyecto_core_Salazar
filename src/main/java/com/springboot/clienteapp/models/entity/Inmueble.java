package com.springboot.clienteapp.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inmuebles")
public class Inmueble implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Inmueble;

    //private Integer clientes_id;
    
    @NotNull
    private Integer area;

    @NotEmpty
    private String tipo;

    @NotEmpty
    private String sector;

    @NotNull
    private Integer dormitorios;

    private Integer banios;

    @DecimalMin("0.0")
    private Float precio;

    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;
    
	public Integer getIdInmueble() {
		return id_Inmueble;
	}
		
	
	

	public Inmueble() {
		super();
	}




	public Inmueble(Integer id_Inmueble, @NotNull Integer area, @NotEmpty String tipo, @NotEmpty String sector,
			@NotNull Integer dormitorios, Integer banios, @DecimalMin("0.0") Float precio) {
		super();
		this.id_Inmueble = id_Inmueble;
		this.area = area;
		this.tipo = tipo;
		this.sector = sector;
		this.dormitorios = dormitorios;
		this.banios = banios;
		this.precio = precio;
	}




	public void setIdInmueble(Integer idInmueble) {
		this.id_Inmueble = idInmueble;
	}	
	
	/*public Integer getClientes_id() {
		return clientes_id;
	}

	public void setClientes_id(Integer clientes_id) {
		this.clientes_id = clientes_id;
	}*/

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Integer getDormitorios() {
		return dormitorios;
	}

	public void setDormitorios(Integer dormitorios) {
		this.dormitorios = dormitorios;
	}

	public Integer getBanios() {
		return banios;
	}

	public void setBanios(Integer banios) {
		this.banios = banios;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Inmueble [idInmueble=" + id_Inmueble + ", area=" + area + ", tipo=" + tipo + ", sector=" + sector
				+ ", dormitorios=" + dormitorios + ", banios=" + banios + ", precio=" + precio + ", descripcion="
				+ descripcion + ", cliente=" + cliente + "]";
	}

	
		
	
	
}
