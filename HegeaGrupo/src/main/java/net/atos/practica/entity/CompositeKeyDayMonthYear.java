package net.atos.practica.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class CompositeKeyDayMonthYear implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="year", nullable = false)
	private int year;
	
	public CompositeKeyDayMonthYear(int year, String mes, String actividad) {
		
		this.year = year;
		this.mes = mes;
		this.actividad = actividad;
	}

	@Column(name="mes", nullable = false)
	private String mes;
	@Column(name="actividad", nullable = false)
	private String actividad;
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}

public CompositeKeyDayMonthYear() {
	
	
}
	
}
