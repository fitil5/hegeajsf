package net.atos.practica.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="actividades")
public class ActividadEntity  implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	private String nombre_ac;
	public String getNombre_ac() {
		return nombre_ac;
	}
	public void setNombre_ac(String nombre_ac) {
		this.nombre_ac = nombre_ac;
	}
	
}
