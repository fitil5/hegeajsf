package net.atos.practica.entity;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="alta_actividad")
@IdClass(CompositeKeyAltaActividad.class)
public class AltaActividadEntity {
	@Id
	private String username;
	@Id
	private String actividad;
	@Id
	private String fecha_actividad;



	
	
	public String getFecha_actividad() {
		return fecha_actividad;
	}
	public void setFecha_actividad(String fecha_actividad) {
		this.fecha_actividad = fecha_actividad;
	}
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
	


	

}
