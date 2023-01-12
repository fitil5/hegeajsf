package net.atos.practica.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proyectos")
public class ProyectoEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	private String nombre_pr;

	public String getNombre_pr() {
		return nombre_pr;
	}

	public void setNombre_pr(String nombre_pr) {
		this.nombre_pr = nombre_pr;
	}

	

}
