package com.springboot.clienteapp.models.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //Indicamos que es la clave de la tabla
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Indicamos que se genera la clave automaticamente
	private Long id;
	
	@NotEmpty
	private String nombres;
	
	@NotEmpty
	private String apellidos;
	
	//@NotEmpty
	@Pattern(regexp="[0-9]{4}-[0-9]{3}") //video crud 12
	private String telefono;
	
	@NotEmpty
	@Email
	private String email;
	
	
	@ManyToOne //Indicamos que la relacion con la otra tabla es de mnnuchos a uno
	@JoinColumn(name="ciudades_id") //indicamos el campo con el que esta relacionada la tabla
	private Ciudad ciudad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", email=" + email + ", ciudad=" + ciudad + "]";
	}
	
	
}

