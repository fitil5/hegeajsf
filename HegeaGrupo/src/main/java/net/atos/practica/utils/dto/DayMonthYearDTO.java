package net.atos.practica.utils.dto;

public class DayMonthYearDTO {
private Integer year;
private String mes;
private String actividad;
private Boolean lunes;
private Boolean martes;
private Boolean miercoles;
private Boolean jueves;
private Boolean viernes;
public Integer getYear() {
	return year;
}
public void setYear(Integer year) {
	this.year = year;
}
public String getMes() {
	return mes;
}
public void setMes(String mes) {
	this.mes = mes;
}
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
public String getActividad() {
	return actividad;
}
public void setActividad(String actividad) {
	this.actividad = actividad;
}

}
