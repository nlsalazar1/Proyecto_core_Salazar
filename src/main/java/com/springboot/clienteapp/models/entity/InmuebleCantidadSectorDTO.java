package com.springboot.clienteapp.models.entity;

import java.math.BigInteger;

public class InmuebleCantidadSectorDTO {
   
	private String sector;
    private BigInteger cantidad;

    public InmuebleCantidadSectorDTO(String sector, BigInteger cantidad) {
        this.sector = sector;
        this.cantidad = cantidad;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

	@Override
	public String toString() {
		return "InmuebleCantidadSectorDTO [sector=" + sector + ", cantidad=" + cantidad + "]";
	}
    
    
}
