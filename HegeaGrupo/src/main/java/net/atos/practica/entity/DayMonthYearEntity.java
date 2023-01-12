package net.atos.practica.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name="diasmesactividad")
public class DayMonthYearEntity {
	
	@EmbeddedId
	private CompositeKeyDayMonthYear compositeKeyDayMonthYear; 
	
	private Boolean lunes;
	private Boolean martes;
	private Boolean miercoles;
	private Boolean jueves;
	private Boolean viernes;
	
	
	
	public Boolean getLunes() {
		return lunes;
	}
	public void setLunes(Boolean lunes) {
		this.lunes = lunes;
	}
	public Boolean getMartes() {
		return martes;
	}
	public void setMartes(Boolean martes) {
		this.martes = martes;
	}
	public Boolean getMiercoles() {
		return miercoles;
	}
	public void setMiercoles(Boolean miercoles) {
		this.miercoles = miercoles;
	}
	public Boolean getJueves() {
		return jueves;
	}
	public void setJueves(Boolean jueves) {
		this.jueves = jueves;
	}
	public Boolean getViernes() {
		return viernes;
	}
	public void setViernes(Boolean viernes) {
		this.viernes = viernes;
	}
	public CompositeKeyDayMonthYear getCompositeKeyDayMonthYear() {
		return compositeKeyDayMonthYear;
	}
	public void setCompositeKeyDayMonthYear(CompositeKeyDayMonthYear compositeKeyDayMonthYear) {
		this.compositeKeyDayMonthYear = compositeKeyDayMonthYear;
	}

	

}
