package net.atos.practica.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Component;

@Component
@ManagedBean

public class loginController {

	private String mensaje;
	private String mensajeRe;
	private String username;

	public void save() {

		mensaje = mensajeRe;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMensajeRe() {
		return mensajeRe;
	}

	public void setMensajeRe(String mensajeRe) {
		this.mensajeRe = mensajeRe;
	}

}
