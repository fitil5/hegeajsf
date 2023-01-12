package net.atos.practica.models.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.practica.entity.CompositeKey;
import net.atos.practica.entity.CompositeKeyDayMonthYear;
import net.atos.practica.entity.ProyectoEntity;

import net.atos.practica.repository.IActividadesRepository;
import net.atos.practica.repository.IAltaActividadUsuario;
import net.atos.practica.repository.IDayMonthYearRepository;
import net.atos.practica.repository.IFechaActividadRepository;
import net.atos.practica.repository.IOrganizacionesRepository;
import net.atos.practica.repository.IProyectosRepository;
import net.atos.practica.repository.IUsuarioRepository;
import net.atos.practica.utils.converter.Converter;
import net.atos.practica.utils.dto.ActividadDTO;
import net.atos.practica.utils.dto.AltaActividadDTO;
import net.atos.practica.utils.dto.DayMonthYearDTO;
import net.atos.practica.utils.dto.FechaActividadDTO;
import net.atos.practica.utils.dto.OrganizacionDTO;
import net.atos.practica.utils.dto.ProyectoDTO;
import net.atos.practica.utils.dto.UsuarioDTO;

@Service
public class CrudService implements ICrudService {

	@Autowired
	IUsuarioRepository userRepository;
	@Autowired
	IProyectosRepository proyectoRepository;
	
	@Autowired
	IOrganizacionesRepository organizacionesRepository;
	@Autowired
	IActividadesRepository actividadesRepository;
	@Autowired
	IFechaActividadRepository fechaActividadRepository;
	@Autowired
	IAltaActividadUsuario altaActividadUsuario;
	@Autowired
	IDayMonthYearRepository dayMonthYearRepository;

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDTO> Read() {	
		
		return Converter.listUserEntityToListUserDTO(userRepository.findAll());
	}
	@Override
	public void createAltaActividadUsuario(AltaActividadDTO altaActividadDTO ) {
		altaActividadUsuario.save(Converter.altaActividadDTOToAltaActividadEntity(altaActividadDTO));
	}

	@Override
	public void createUser(UsuarioDTO newUsuario) {
	
		userRepository.save( Converter.userDTOToUserEntity(newUsuario));

	}

	@Override
	public void deleteUsuario(List<UsuarioDTO> usuarioList) {
		
		
		userRepository.deleteAll(Converter.listListUserDTOToUserEntity(usuarioList));
		
	}
	@Override
	public void deleteProyecto(List<ProyectoDTO> proyectoList) {
		
		proyectoRepository.deleteAll( Converter.listProyectoDTOTolistproyectoEntity(proyectoList));
	}
	@Override
	public void deleteOrganizacion (List<OrganizacionDTO> organizacionList) {
		 organizacionesRepository.deleteAll(Converter.listOrganizacionDTOToListOrganizacionEntity(organizacionList));
	}
	
	@Override
	public void createOrganizacion(OrganizacionDTO newOrganizacion) {
		
		organizacionesRepository.save(Converter.organizacionDTOToOrganizacionEntity(newOrganizacion));

	}
	@Override
	public List<String> findProyectosCrudController() {
		List<String> listaString = new ArrayList<>();

		for (ProyectoEntity pr : (proyectoRepository.findAll())) {

			listaString.add(pr.getNombre_pr());
		}
		return listaString;
	}

	@Override
	public List<String> findOrganizaciones() {
		
		return Converter.listOrganizacionEntityToStringList( organizacionesRepository.findAll());
	}
	@Override
	public List<OrganizacionDTO> findOrganizacionesDTO() {	
		
		return Converter.listOrganizacionEntityToOrganizacionDTOList( organizacionesRepository.findAll());
	}
	@Override
	public List<ProyectoDTO> findProyectos() {
		
		return Converter.listproyectoEntityTolistProyectoDTO(proyectoRepository.findAll());
	}

	@Override
	public void createProyecto(ProyectoDTO newProyecto) {
	

		proyectoRepository.save(Converter.proyectoDTOToproyectoEntity(newProyecto));
	}
	@Override
	public List<ActividadDTO> findActividades() {			
		return Converter.listActividadEntityToListActividadDTO(actividadesRepository.findAll());
	}
	@Override
	public void createActividad(ActividadDTO newActividad) {
		

		actividadesRepository.save(Converter.actividadDTOToActividadEntity(newActividad));
	}
	@Override
	public void createFechaActividad(FechaActividadDTO newFechaActividad) {
		

		try {
			fechaActividadRepository.save(Converter.fechaactividadDTOToFechaActividadEntity(newFechaActividad));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void deleteActividad(List<ActividadDTO> listActividadDTO) {
		 
		
		actividadesRepository.deleteAll(Converter.listActividadDTOToListActividadEntity(listActividadDTO));
	}	
	
	@Override
	public List<FechaActividadDTO> findFechaActividades() {
		
		return Converter.fechaactividadEntityListToFechaActividadDTOList(fechaActividadRepository.findAll());
	}
	@Override
	public DayMonthYearDTO findByDayMonthActividades(int year,String mes,String actividad) {
		
		return Converter.dayMonthYearEntityTodayMonthYearDTO(dayMonthYearRepository.findByCompositeKeyDayMonthYear(new CompositeKeyDayMonthYear(year,mes,actividad)));
	
	}
	@Override
	public void asignarDiasSemana(DayMonthYearDTO dayMonthYearDTO) {
		
		dayMonthYearRepository.save(Converter.dayMonthYearDTOTodayMonthYearEntity(dayMonthYearDTO));
	}
	@Override
	public Boolean comprobarFechaActividad(String actividad,String fecha   ) {
		  SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.ROOT);
		try {
			return fechaActividadRepository.existsById(new CompositeKey(actividad,format.parse(fecha)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
