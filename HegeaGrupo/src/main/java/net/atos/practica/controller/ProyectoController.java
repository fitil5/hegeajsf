package net.atos.practica.controller;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.atos.practica.models.service.ICrudService;

import net.atos.practica.utils.dto.ProyectoDTO;





@Component
@ManagedBean
public class ProyectoController  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Autowired
	ICrudService crudService;
	private List<ProyectoDTO> listproyectoDto;
	private ProyectoDTO newProyecto;
	private List<ProyectoDTO> selectedList;
	private List<ProyectoDTO> searchProyectos;
	//private List<ProyectoDTO> listproyectoDto;
	//= ArrayList<>();
	@PostConstruct
	public void OnInit() {
		newProyecto = new ProyectoDTO();
		setNewProyecto(new ProyectoDTO());
		setListproyectoDto(crudService.findProyectos());
	}
	public List<ProyectoDTO> getListproyectoDto() {
		return listproyectoDto;
	}
	public void setListproyectoDto(List<ProyectoDTO> listproyectoDto) {
		this.listproyectoDto = listproyectoDto;
	}
	public void createNewProyecto() {	
	
		crudService.createProyecto(newProyecto);
		setListproyectoDto(crudService.findProyectos());
	}
	public ProyectoDTO getNewProyecto() {
		return newProyecto;
	}
	public void setNewProyecto(ProyectoDTO newProyecto) {
		this.newProyecto = newProyecto;
	}
	public List<ProyectoDTO> getSelectedList() {
		return selectedList;
	}
	public void setSelectedList(List<ProyectoDTO> selectedList) {
		this.selectedList = selectedList;
	}
	public void onRowEditProyecto(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("User Edited",
				String.valueOf((((ProyectoDTO) event.getObject()).getNombre_pr())));
		FacesContext.getCurrentInstance().addMessage(null, msg);

		crudService.createProyecto((ProyectoDTO) event.getObject());

	}

	public void onRowCancelProyecto(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				String.valueOf((((ProyectoDTO) event.getObject()).getNombre_pr())));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	public List<ProyectoDTO> getSearchProyectos() {
		return searchProyectos;
	}
	public void setSearchProyectos(List<ProyectoDTO> searchProyectos) {
		this.searchProyectos = searchProyectos;
	}
	public void delete() {
		crudService.deleteProyecto(selectedList);
		setListproyectoDto(crudService.findProyectos());
	}
	
	


}
