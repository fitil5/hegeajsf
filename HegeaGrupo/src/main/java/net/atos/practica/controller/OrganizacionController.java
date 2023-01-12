package net.atos.practica.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.atos.practica.models.service.ICrudService;
import net.atos.practica.utils.dto.OrganizacionDTO;



@ManagedBean
@Component
public class OrganizacionController implements Serializable{

	/**
	 * 
	 */
	@Autowired
	ICrudService crudService;
	private static final long serialVersionUID = 1L;
	
	private List<OrganizacionDTO> organizacionDTOList;
	private List<OrganizacionDTO> searchOrga;
	private OrganizacionDTO newOrganizacion;
	private List<OrganizacionDTO> selectedList;
	@PostConstruct
	public void OnInit() {
		setNewOrganizacion(new OrganizacionDTO());
		setOrganizacionDTOList(crudService.findOrganizacionesDTO());
		
	}
	public void createNewOrganizacion() {	
		
		crudService.createOrganizacion(newOrganizacion);
		setOrganizacionDTOList(crudService.findOrganizacionesDTO());
	}
	public void delete() {
		crudService.deleteOrganizacion(selectedList);
		setOrganizacionDTOList(crudService.findOrganizacionesDTO());
	}

	public List<OrganizacionDTO> getOrganizacionDTOList() {
		return organizacionDTOList;
	}

	public void setOrganizacionDTOList(List<OrganizacionDTO> organizacionDTOList) {
		this.organizacionDTOList = organizacionDTOList;
	}

	public List<OrganizacionDTO> getSearchOrga() {
		return searchOrga;
	}

	public void setSearchOrga(List<OrganizacionDTO> searchOrga) {
		this.searchOrga = searchOrga;
	}

	public OrganizacionDTO getNewOrganizacion() {
		return newOrganizacion;
	}

	public void setNewOrganizacion(OrganizacionDTO newOrganizacion) {
		this.newOrganizacion = newOrganizacion;
	}

	public List<OrganizacionDTO> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<OrganizacionDTO> selectedList) {
		this.selectedList = selectedList;
	}

	

}
