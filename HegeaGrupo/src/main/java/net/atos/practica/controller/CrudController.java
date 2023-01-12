package net.atos.practica.controller;

import java.io.Serializable;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.atos.practica.models.service.ICrudService;

import net.atos.practica.utils.dto.UsuarioDTO;

@ManagedBean
@Component
public class CrudController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ICrudService crudService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private List<UsuarioDTO> selectedList;
	private List<UsuarioDTO> listUsersDto;
	private UsuarioDTO newUsuario;
	private String PassDefecto;
	private List<UsuarioDTO> searchUsers;
	
	private List<String> proyectoList;
	private List<String> organizacionList;
	 private String selectedProyecto; 
	

	@PostConstruct
	public void OnInit() {
		setListUsersDto(crudService.Read());
		setNewUsuario(new UsuarioDTO());
		setPassDefecto("Atos@2018");
		setProyectoList(crudService.findProyectosCrudController());
		setOrganizacionList(crudService.findOrganizaciones());
		
		
	}

	public void onLoad() {
		// setListUsersDto(crudService.Read());
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("User Edited",
				String.valueOf((((UsuarioDTO) event.getObject()).getUsername())));
		FacesContext.getCurrentInstance().addMessage(null, msg);

		crudService.createUser(((UsuarioDTO) event.getObject()));

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				String.valueOf((((UsuarioDTO) event.getObject()).getUsername())));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void createUser() {
		String bcryptPassword = passwordEncoder.encode(PassDefecto);
		newUsuario.setPassword(bcryptPassword);
		newUsuario.setChangepassword(false);
		crudService.createUser(newUsuario);
		newUsuario.setUsername("");
		newUsuario.setApellidos("");
		newUsuario.setNombre("");
		newUsuario.setAdmin(false);
		newUsuario.setEnabled(false);
		newUsuario.setProyecto("");
		newUsuario.setOrganizacion(""); 
		setListUsersDto(crudService.Read());

	}

	public void delete() {
		crudService.deleteUsuario(selectedList);
		setListUsersDto(crudService.Read());
	}

	public void read() {
		listUsersDto = crudService.Read();
	}
	
	

	public List<UsuarioDTO> getListUsersDto() {
		return listUsersDto;
	}

	public void setListUsersDto(List<UsuarioDTO> listUsersDto) {
		this.listUsersDto = listUsersDto;
	}

	public UsuarioDTO getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(UsuarioDTO newUsuario) {
		this.newUsuario = newUsuario;
	}

	public String getPassDefecto() {
		return PassDefecto;
	}

	public void setPassDefecto(String passDefecto) {
		PassDefecto = passDefecto;
	}

	public List<UsuarioDTO> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<UsuarioDTO> selectedList) {
		this.selectedList = selectedList;
	}

	public List<UsuarioDTO> getSearchUsers() {
		return searchUsers;
	}

	public void setSearchUsers(List<UsuarioDTO> searchUsers) {
		this.searchUsers = searchUsers;
	}

	public List<String> getProyectoList() {
		return proyectoList;
	}

	public void setProyectoList(List<String> proyectoList) {
		this.proyectoList = proyectoList;
	}

	


	public String getSelectedProyecto() {
		return selectedProyecto;
	}

	public void setSelectedProyecto(String selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}

	public List<String> getOrganizacionList() {
		return organizacionList;
	}

	public void setOrganizacionList(List<String> organizacionList) {
		this.organizacionList = organizacionList;
	}



	

}
