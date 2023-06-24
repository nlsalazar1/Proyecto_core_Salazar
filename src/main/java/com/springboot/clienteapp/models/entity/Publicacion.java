package com.springboot.clienteapp.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "publicaciones")
public class Publicacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private int id_Publicacion;
    
    @ManyToOne
    @JoinColumn(name = "inmueble_id")
    private Inmueble inmueble;
    
    @Column(name = "fecha_publicacion")
    private Date fechaPublicacion;
    
    @Column(name = "vistas")
    private int vistas;

    // Constructor vacío (requerido por JPA)
    public Publicacion() {
    }

	public int getIdPublicacion() {
		return id_Publicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.id_Publicacion = idPublicacion;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getVistas() {
		return vistas;
	}

	public void setVistas(int vistas) {
		this.vistas = vistas;
	}

	@Override
	public String toString() {
		return "Publicacion [idPublicacion=" + id_Publicacion + ", inmueble=" + inmueble + ", fechaPublicacion="
				+ fechaPublicacion + ", vistas=" + vistas + "]";
	}

    
    
}
