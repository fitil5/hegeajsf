package net.atos.practica.entity;

import java.io.Serializable;

public class CompositeKeyAltaActividad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String actividad;
	private String fecha_actividad;



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getFecha_actividad() {
		return fecha_actividad;
	}
	public void setFecha_actividad(String fecha_actividad) {
		this.fecha_actividad = fecha_actividad;
	}
	
}
