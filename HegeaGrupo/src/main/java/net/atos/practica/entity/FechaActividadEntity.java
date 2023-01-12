package net.atos.practica.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="fechaactividad")
@IdClass(CompositeKey.class)
public class FechaActividadEntity  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Id
	private Date fecha;
	 @Id
	private String nombre_ac;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombre_ac() {
		return nombre_ac;
	}
	public void setNombre_ac(String nombre_ac) {
		this.nombre_ac = nombre_ac;
	}
}
