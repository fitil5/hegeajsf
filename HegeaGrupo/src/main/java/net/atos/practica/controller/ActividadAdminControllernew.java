package net.atos.practica.controller;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.hibernate.sql.Select;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.atos.practica.models.service.ICrudService;
import net.atos.practica.utils.dto.ActividadDTO;
import net.atos.practica.utils.dto.DayMonthYearDTO;
import net.atos.practica.utils.dto.FechaActividadDTO;






@ManagedBean
@Component
public class ActividadAdminControllernew implements Serializable {

	/**
	 * 
	 */
	@Autowired
	ICrudService crudService;
	private static final long serialVersionUID = 1L;
	private List<ActividadDTO> listActividadDTO;
	private ActividadDTO newActividad;
	private FechaActividadDTO newFechaActividad;
	private List<FechaActividadDTO> listFechaActividadDTO;
	private List<FechaActividadDTO> searchFechaActividad;
	private List<String> nombreActividad = new ArrayList<>();
	private List<ActividadDTO> selectedListActidades;
	private List<ActividadDTO> searchActividades;
	private List<FechaActividadDTO>  newFechaActividadList = new ArrayList<>();
	//////
	
	public List<FechaActividadDTO> getNewFechaActividadList() {
		return newFechaActividadList;
	}
	public void setNewFechaActividadList(List<FechaActividadDTO> newFechaActividadList) {
		this.newFechaActividadList = newFechaActividadList;
	}
	private List<String> diaSemana = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");
	private List<String> meses = Arrays.asList("January", " February", "March","April","May","June","July","August","September","October","November","December");
	private List<Integer> year= new ArrayList<>();
	private DayMonthYearDTO dayMonthYearDTO ;
	
	@PostConstruct
	public void OnInit() {
		setListActividadDTO(crudService.findActividades());
		 iniNombreActividad();
		 listFechaActividadDTO= crudService.findFechaActividades();
		newFechaActividad = new FechaActividadDTO();
		newActividad = new ActividadDTO();
		//////
		iniYearList();
		findDiasSemanaAsignados();
		
		
		
	}

	     
	   
	
	 public List<FechaActividadDTO> extraerSemanaMes(DayMonthYearDTO dayMonthYearDTO){
		 List<FechaActividadDTO>  extraerSemanaMesFechaActividadList = new ArrayList<>();
		 if (dayMonthYearDTO.getLunes()) {
			 extraerSemanaMesFechaActividadList.addAll(extraerDiasMes(dayMonthYearDTO.getYear(),dayMonthYearDTO.getMes(),DayOfWeek.MONDAY,dayMonthYearDTO.getActividad()));
		 }
		 if (dayMonthYearDTO.getMartes()) {
			 extraerSemanaMesFechaActividadList.addAll(extraerDiasMes(dayMonthYearDTO.getYear(),dayMonthYearDTO.getMes(),DayOfWeek.TUESDAY,dayMonthYearDTO.getActividad()));
		 }
		 if (dayMonthYearDTO.getMiercoles()) {
			 extraerSemanaMesFechaActividadList.addAll(extraerDiasMes(dayMonthYearDTO.getYear(),dayMonthYearDTO.getMes(),DayOfWeek.WEDNESDAY,dayMonthYearDTO.getActividad()));
		 }
		 if (dayMonthYearDTO.getJueves()) {
			 extraerSemanaMesFechaActividadList.addAll( extraerDiasMes(dayMonthYearDTO.getYear(),dayMonthYearDTO.getMes(),DayOfWeek.THURSDAY,dayMonthYearDTO.getActividad()));
		 }
		 if (dayMonthYearDTO.getViernes()) {
			 extraerSemanaMesFechaActividadList.addAll(extraerDiasMes(dayMonthYearDTO.getYear(),dayMonthYearDTO.getMes(),DayOfWeek.FRIDAY,dayMonthYearDTO.getActividad()));
		 }
		 return extraerSemanaMesFechaActividadList;
		 
	 }
	 public  List<FechaActividadDTO>  extraerDiasMes(int year,String mes,DayOfWeek dia,String actividad) {
		
		
		 List<FechaActividadDTO>  FechaActividadextraerDiasMes = new ArrayList<>();
		 Month month = Month.valueOf(mes.toUpperCase());
	        LocalDate date = Year.of(year).atMonth(month).atDay(1).
	              with(TemporalAdjusters.firstInMonth(dia));
	        Month mi = date.getMonth();
	        while (mi == month) {
	        	 FechaActividadDTO extraerDiasMesfechaActividadDTO = new FechaActividadDTO();
	        	extraerDiasMesfechaActividadDTO.setFecha(String.valueOf(date));
	        	extraerDiasMesfechaActividadDTO.setNombre_act(actividad);
	        	FechaActividadextraerDiasMes.add(extraerDiasMesfechaActividadDTO);
	            date = date.with(TemporalAdjusters.next(dia));
	            mi = date.getMonth();
	        }
	        return FechaActividadextraerDiasMes;
	 }
	 
	
	
	
	
	public void iniYearList() {
		for (int i =2018; i<2119; i++) {
			 year.add(i);
		}
	}
	public void findDiasSemanaAsignados() {
		try {
			dayMonthYearDTO=crudService.findByDayMonthActividades(dayMonthYearDTO.getYear(),dayMonthYearDTO.getMes(),dayMonthYearDTO.getActividad());
		}
		catch(NullPointerException ex) {
			initDto();
		}
		
	}
	public void onYearChange(final AjaxBehaviorEvent event) {
		/*if(dayMonthYearDTO.getMes()!=null && dayMonthYearDTO.getActividad()!=null &&dayMonthYearDTO.getYear()!=null ) {
			findDiasSemanaAsignados();
			initDto()  ;
		}*/
		//dayMonthYearDTO.setYear((event.ge);
		
	}
	
	
	public void onMesChange() {
		/*if(dayMonthYearDTO.getYear()!=null && dayMonthYearDTO.getActividad()!=null && dayMonthYearDTO.getMes()!=null) {
			findDiasSemanaAsignados();
			initDto()  ;
		}*/
	}
	public void onActividadChange() {
		/*if(dayMonthYearDTO.getYear()!=null && dayMonthYearDTO.getMes()!=null && dayMonthYearDTO.getActividad()!=null) {
			findDiasSemanaAsignados();
			initDto()  ;
		}*/
	}
	public void initDto() {
		dayMonthYearDTO = new DayMonthYearDTO();
		dayMonthYearDTO.setLunes(false);
		dayMonthYearDTO.setMartes(false);
		dayMonthYearDTO.setMiercoles(false);
		dayMonthYearDTO.setJueves(false);
		dayMonthYearDTO.setViernes(false);
	}
	public void createNewActividad() {	
		
		crudService.createActividad(newActividad);
		setListActividadDTO(crudService.findActividades());
	}
	public void cargarTablaNuevesActividades() {
		//crudService.asignarDiasSemana(dayMonthYearDTO);
		newFechaActividadList= new ArrayList<>();
		
		newFechaActividadList= extraerSemanaMes(dayMonthYearDTO);
	}
	public void createNewFechaActividad() {	
		
		crudService.createFechaActividad(newFechaActividad);
		//setListActividadDTO(crudService.findActividades());
	}
	public void delete() {
	crudService.deleteActividad(selectedListActidades);
	setListActividadDTO(crudService.findActividades());
	}



	public List<ActividadDTO> getListActividadDTO() {
		return listActividadDTO;
	}
	public int diaSemana(Date diaSeleccionado) {
		Calendar c = Calendar.getInstance();
		c.setTime(diaSeleccionado);
		return c.get(Calendar.DAY_OF_WEEK);
	}
	 public void  addMessage() {
		 cargarTablaNuevesActividades();
		 if(diasSelecionados()>3) {
			  FacesContext facesContext = FacesContext.getCurrentInstance();
			  facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Maximo tres dias", null));
			  initDto();
		 }
	 }

		  
	
public int diasSelecionados() {
	int i=0;
	if(dayMonthYearDTO.getLunes()) {i++;}
	if(dayMonthYearDTO.getMartes()) {i++;}
	if(dayMonthYearDTO.getMiercoles()) {i++;}
	if(dayMonthYearDTO.getJueves()) {i++;}
	if(dayMonthYearDTO.getViernes()) {i++;}
	return i;
}


	public void setListActividadDTO(List<ActividadDTO> listActividadDTO) {
		this.listActividadDTO = listActividadDTO;
	}
	public ActividadDTO getNewActividad() {
		return newActividad;
	}
	public void setNewActividad(ActividadDTO newActividad) {
		this.newActividad = newActividad;
	}
	public FechaActividadDTO getNewFechaActividad() {
		return newFechaActividad;
	}
	public void setNewFechaActividad(FechaActividadDTO newFechaActividad) {
		this.newFechaActividad = newFechaActividad;
	}
	public List<String> getNombreActividad() {
		return nombreActividad;
	}
	public void setNombreActividad(List<String> nombreActividad) {
		this.nombreActividad = nombreActividad;
	}
	public void iniNombreActividad() {
		for (ActividadDTO actividadDTO : listActividadDTO) {
			nombreActividad.add(actividadDTO.getNombre_ac());
		}
		
	}
	public void onRowEditActividad(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Actividad Editada",
				String.valueOf((((ActividadDTO) event.getObject()).getNombre_ac())));
		FacesContext.getCurrentInstance().addMessage(null, msg);

		crudService.createActividad((ActividadDTO) event.getObject());
		

	}

	public void onRowCancelActividad(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				String.valueOf((((ActividadDTO) event.getObject()).getNombre_ac())));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void onRowEditFechaActividad(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Fecha Editada",
				String.valueOf((((FechaActividadDTO) event.getObject()).getNombre_act())));
		FacesContext.getCurrentInstance().addMessage(null, msg);

		crudService.createFechaActividad((FechaActividadDTO) event.getObject());
		 listFechaActividadDTO= crudService.findFechaActividades();

	}

	public void onRowCancelFechaActividad(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				String.valueOf((((FechaActividadDTO) event.getObject()).getNombre_act())));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		//crudService.createFechaActividad(((FechaActividadDTO) event.getObject()));
	}

	public List<ActividadDTO> getSearchActividades() {
		return searchActividades;
	}
	public void setSearchActividades(List<ActividadDTO> searchActividades) {
		this.searchActividades = searchActividades;
	}
	public List<FechaActividadDTO> getListFechaActividadDTO() {
		return listFechaActividadDTO;
	}
	public void setListFechaActividadDTO(List<FechaActividadDTO> listFechaActividadDTO) {
		this.listFechaActividadDTO = listFechaActividadDTO;
	}

	public List<ActividadDTO> getSelectedListActidades() {
		return selectedListActidades;
	}
	public void setSelectedListActidades(List<ActividadDTO> selectedListActidades) {
		this.selectedListActidades = selectedListActidades;
	}
	public List<FechaActividadDTO> getSearchFechaActividad() {
		return searchFechaActividad;
	}
	public void setSearchFechaActividad(List<FechaActividadDTO> searchFechaActividad) {
		this.searchFechaActividad = searchFechaActividad;
	}
	public List<String> getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(List<String> diaSemana) {
		this.diaSemana = diaSemana;
	}
	public List<String> getMeses() {
		return meses;
	}
	public void setMeses(List<String> meses) {
		this.meses = meses;
	}
	public List<Integer> getYear() {
		return year;
	}
	public void setYear(List<Integer> year) {
		this.year = year;
	}
	public DayMonthYearDTO getDayMonthYearDTO() {
		return dayMonthYearDTO;
	}
	public void setDayMonthYearDTO(DayMonthYearDTO dayMonthYearDTO) {
		this.dayMonthYearDTO = dayMonthYearDTO;
	}

	


	


	


	


	
	
	
	
	
}




