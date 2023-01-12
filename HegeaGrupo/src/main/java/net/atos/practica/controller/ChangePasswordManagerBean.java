package net.atos.practica.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.atos.practica.auth.handler.CustomizeAuthenticationSuccessHandler;
import net.atos.practica.models.service.IChangePasswordService;

@ManagedBean
@Component
public class ChangePasswordManagerBean {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler; 
	@Autowired
	private IChangePasswordService changeService;

	private String das;
	private String pass1;
	private String pass2;

	public void changePassword() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		das = authentication.getName();
		if (pass1.equals(pass2)) {
			String bcryptPassword = passwordEncoder.encode(pass1);
			changeService.updatePrimerAcceso(das, bcryptPassword);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			try {
				if (customizeAuthenticationSuccessHandler.adminchange) {
					facesContext.getExternalContext().redirect("/pages/admin/homeadmin.xhtml");
				} else {
					facesContext.getExternalContext().redirect("/pages/user/homeuser.xhtml");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Las contraseñas no coinciden"));
		}

	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public String getDas() {
		return das;
	}

	public void setDas(String das) {
		this.das = das;
	}

}
