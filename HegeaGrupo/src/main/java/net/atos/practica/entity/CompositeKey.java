package net.atos.practica.entity;

import java.io.Serializable;
import java.util.Date;

public class CompositeKey implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre_ac;
   
	public CompositeKey() {
		
	}
	private Date fecha;
	 public CompositeKey(String nombre_ac, Date fecha) {
			
			this.nombre_ac = nombre_ac;
			this.fecha = fecha;
		}
	
	public String getNombre_ac() {
		return nombre_ac;
	}
	public void setNombre_ac(String nombre_ac) {
		this.nombre_ac = nombre_ac;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
