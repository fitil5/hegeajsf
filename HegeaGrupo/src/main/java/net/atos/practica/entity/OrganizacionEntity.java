package net.atos.practica.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="organizaciones")
public class OrganizacionEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String nombre_or;
	
	
	public String getNombre_or() {
		return nombre_or;
	}
	public void setNombre_or(String nombre_or) {
		this.nombre_or = nombre_or;
	}
	
}
