package net.atos.practica.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import net.atos.practica.models.service.ICrudService;
import net.atos.practica.utils.dto.AltaActividadDTO;
import net.atos.practica.utils.dto.FechaActividadDTO;

@ManagedBean(name="actividadUserController")
@Component
public class ActividadUserController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ICrudService crudService;
	private List<String> actividades = new ArrayList<>();
	private List<FechaActividadDTO> listFechaActividades;
	private List <AltaActividadDTO> listAltaActividad;
	private AltaActividadDTO newAltaActividad;
	private Date minDay;
	private Date maxDay=new Date();
	
	private Boolean primerasemana= false;
	
	
	@PostConstruct
	public void OnInit() {
		init();
	}
	public void init() {
		listFechaActividades= crudService.findFechaActividades();
		actividades= distinctListActividadesConFecha(listFechaActividades);
		newAltaActividad= new  AltaActividadDTO();
		iniDias();
	}
	public List<String> distinctListActividadesConFecha(List<FechaActividadDTO> listFechaActividades) {
	    List<String> listDistinct = new ArrayList<>();
	    for(FechaActividadDTO i : listFechaActividades)
	    {
	        if( !listDistinct.contains(i.getNombre_act()) ) { listDistinct.add(i.getNombre_act()); }
	    }
	    return listDistinct;
	}
	public void onDateSelect(SelectEvent event) throws ParseException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.ROOT);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
        //newAltaActividad.setFecha_actividad(String.valueOf(event.getObject()));
    }

public List<String> getActividades() {
	return actividades;
}
public void setActividades(List<String> actividades) {
	this.actividades = actividades;
}
public List <AltaActividadDTO> getListAltaActividad() {
	return listAltaActividad;
}
public void setListAltaActividad(List <AltaActividadDTO> listAltaActividad) {
	this.listAltaActividad = listAltaActividad;
}
public List<FechaActividadDTO> getListFechaActividades() {
	return listFechaActividades;
}
public void setListFechaActividades(List<FechaActividadDTO> listFechaActividades) {
	this.listFechaActividades = listFechaActividades;
}
public AltaActividadDTO getNewAltaActividad() {
	return newAltaActividad;
}
public void setNewAltaActividad(AltaActividadDTO newAltaActividad) {
	this.newAltaActividad = newAltaActividad;
}
public Date getMinDay() {
	return minDay;
}
public void setMinDay(Date minDay) {
	this.minDay = minDay;
}
public Date getMaxDay() {
	return maxDay;
}
public void setMaxDay(Date maxDay) {
	this.maxDay = maxDay;
}

public void iniDias() {
	Calendar calendar = Calendar.getInstance();
	if( calendar.get(Calendar.DAY_OF_MONTH) <8) {
		primerasemana=true;
	} 
	calendar.add(Calendar.MONTH, 1);
	calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
	minDay = calendar.getTime();
	calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	maxDay = calendar.getTime();
}
public Boolean getPrimerasemana() {
	return primerasemana;
}
public void setPrimerasemana(Boolean primerasemana) {
	this.primerasemana = primerasemana;
}
public void createNewAltaActividad() {
	newAltaActividad.setUsername(getUsername());
	if(getPrimerasemana()) {
		if(crudService.comprobarFechaActividad(newAltaActividad.getActividad(), newAltaActividad.getFecha_actividad())) {
			crudService.createAltaActividadUsuario(newAltaActividad);
		}
		else {
			 FacesContext facesContext = FacesContext.getCurrentInstance();
			  facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La actividad o la fecha seleccionada no es valida", null));
		}
	}
	else {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		  facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Solo puede darse de alta los primeros siete dias del mes", null));
	}
	
	 
}
public String getUsername() {
	
	return  SecurityContextHolder.getContext().getAuthentication().getName();
}

    

	



}
