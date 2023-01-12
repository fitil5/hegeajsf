package net.atos.practica.utils.converter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.atos.practica.entity.ActividadEntity;
import net.atos.practica.entity.AltaActividadEntity;
import net.atos.practica.entity.CompositeKeyDayMonthYear;
import net.atos.practica.entity.DayMonthYearEntity;
import net.atos.practica.entity.FechaActividadEntity;
import net.atos.practica.entity.OrganizacionEntity;
import net.atos.practica.entity.ProyectoEntity;
import net.atos.practica.entity.RoleEntity;

import net.atos.practica.entity.UsuarioEntity;
import net.atos.practica.utils.dto.ActividadDTO;
import net.atos.practica.utils.dto.AltaActividadDTO;
import net.atos.practica.utils.dto.DayMonthYearDTO;
import net.atos.practica.utils.dto.FechaActividadDTO;
import net.atos.practica.utils.dto.OrganizacionDTO;
import net.atos.practica.utils.dto.ProyectoDTO;
import net.atos.practica.utils.dto.UsuarioDTO;

public class Converter {

	public static UsuarioEntity userDTOToUserEntity(UsuarioDTO userDTO) {
		UsuarioEntity userEntity = new UsuarioEntity();
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setChangepassword(userDTO.getChangepassword());
		userEntity.setEnabled(userDTO.getEnabled());
		userEntity.setProyecto(userDTO.getProyecto());
		userEntity.setOrganizacion(userDTO.getOrganizacion());
 		userEntity.setApellidos(userDTO.getApellidos());
		userEntity.setNombre(userDTO.getNombre());
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setUser_id(userDTO.getUsername());
		if (userDTO.getAdmin()) {
			
			roleEntity.setAuthority("ROLE_ADMIN");
			
		} else {
			roleEntity.setAuthority("ROLE_USER");
		}
		userEntity.setRoles(roleEntity);

		return userEntity;
	}

	// UsuarioEntity a usuarioDTO
	public static UsuarioDTO userEntityToUserDTO(UsuarioEntity userEntity) {
		UsuarioDTO userDTO = new UsuarioDTO();
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setChangepassword(userEntity.getChangepassword());
		userDTO.setEnabled(userEntity.getEnabled());
		userDTO.setProyecto(userEntity.getProyecto());
		userDTO.setOrganizacion(userEntity.getOrganizacion());
		userDTO.setApellidos(userEntity.getApellidos());
		userDTO.setNombre(userEntity.getNombre());
		if(userEntity.getRoles().getAuthority().contains("ROLE_ADMIN")) {
			userDTO.setAdmin(true);
		}
		else  {
			userDTO.setAdmin(false);
		}
		
		
		

		return userDTO;
	}
	
	// OrganizacionEntity a OrganizacionDTO
		public static OrganizacionDTO OrganizacionEntityToOrganizacionDTO(OrganizacionEntity organizacionEntity) {
			OrganizacionDTO organizacionDTO = new OrganizacionDTO();
			
			organizacionDTO.setOrganizacion(organizacionEntity.getNombre_or());
			
			return organizacionDTO;
		}

	public static List<UsuarioDTO> listUserEntityToListUserDTO(List<UsuarioEntity> listUserEntity) {
		List<UsuarioDTO> listUserDTO = new ArrayList<>();
		for (UsuarioEntity userEntity : listUserEntity) {
			listUserDTO.add(userEntityToUserDTO(userEntity));
		}
		return listUserDTO;
	}
	//Organizacion String
	public static List<String> listOrganizacionEntityToStringList(List<OrganizacionEntity> listOrgaEntity) {
		List<String> listOrgaDTO = new ArrayList<>();
		for (OrganizacionEntity orgaEntity : listOrgaEntity) {
			
			listOrgaDTO.add(orgaEntity.getNombre_or());
		}
		return listOrgaDTO;
	}
	public static List<OrganizacionDTO> listOrganizacionEntityToOrganizacionDTOList(List<OrganizacionEntity> listOrgaEntity) {
		List<OrganizacionDTO> listOrganizacionDTO = new ArrayList<>();
		for (OrganizacionEntity organizacionEntity : listOrgaEntity) {
			listOrganizacionDTO.add(OrganizacionEntityToOrganizacionDTO( organizacionEntity));
			
		}
		return listOrganizacionDTO;
	}
	public static OrganizacionEntity organizacionDTOToOrganizacionEntity(OrganizacionDTO organizacionDTO) {
		OrganizacionEntity organizacionEntity = new OrganizacionEntity();
		
		organizacionEntity.setNombre_or(organizacionDTO.getOrganizacion());
		return organizacionEntity;
	}
	public static List< OrganizacionEntity> listOrganizacionDTOToListOrganizacionEntity(List<OrganizacionDTO> listOrganizacionDTO){
		List< OrganizacionEntity> listOrganizacionEntity = new ArrayList<>();
		for (OrganizacionDTO organizacionDTO : listOrganizacionDTO) {
			listOrganizacionEntity.add(organizacionDTOToOrganizacionEntity(organizacionDTO));
			
		}
		return listOrganizacionEntity;
	}
	
	

	public static List<UsuarioEntity> listListUserDTOToUserEntity(List<UsuarioDTO> listUserDto) {
		List<UsuarioEntity> listUserEntity = new ArrayList<>();
		for (UsuarioDTO userDto : listUserDto) {
			listUserEntity.add(userDTOToUserEntity(userDto));
		}
		return listUserEntity;
	}
	/*
	 * 
	 * Proyecto
	 */
	public static ProyectoDTO proyectoEntityToProyectoDTO(ProyectoEntity proyectoEntity) {
		ProyectoDTO proyectoDTO = new ProyectoDTO();
		
		proyectoDTO.setNombre_pr(proyectoEntity.getNombre_pr());
		return proyectoDTO;
				
	}
	public static List<ProyectoDTO> listproyectoEntityTolistProyectoDTO(List<ProyectoEntity> listProyectoEntity){
		List<ProyectoDTO> listProyectoDTO = new ArrayList<>();
		for(ProyectoEntity proyectoEntity  : listProyectoEntity ) {
		
			listProyectoDTO.add(proyectoEntityToProyectoDTO( proyectoEntity));
		}
		return listProyectoDTO;
	}
	public static ProyectoEntity proyectoDTOToproyectoEntity(ProyectoDTO proyectoDTO) {
		ProyectoEntity proyectoEntity = new ProyectoEntity();
		
		proyectoEntity.setNombre_pr(proyectoDTO.getNombre_pr());
		return proyectoEntity;
		
	}
	public static List<ProyectoEntity> listProyectoDTOTolistproyectoEntity(List<ProyectoDTO> listProyectoDTO){
		List<ProyectoEntity> listProyectoEntity = new ArrayList<>();
		for(ProyectoDTO proyectoDTO  : listProyectoDTO ) {
			listProyectoEntity.add(proyectoDTOToproyectoEntity( proyectoDTO));
		}
		return listProyectoEntity;
	}
	
	public static ActividadDTO actividadEntityToActividadDTO(ActividadEntity actividadEntity ) {
		ActividadDTO actividadDTO = new ActividadDTO();
	
		actividadDTO.setNombre_ac(actividadEntity.getNombre_ac());
		return actividadDTO;
		
	}
	public static List<ActividadDTO> listActividadEntityToListActividadDTO(List<ActividadEntity> listActividadEntity){
		 List<ActividadDTO> listActividadDTO = new ArrayList<>();
		 for (ActividadEntity actividadEntity :listActividadEntity) {
			 listActividadDTO.add(actividadEntityToActividadDTO(actividadEntity));
		 }
		 return listActividadDTO;
		 
		 
	}
	public static ActividadEntity actividadDTOToActividadEntity(ActividadDTO actividadDTO ) {
		ActividadEntity actividadEntity = new ActividadEntity();
		
		actividadEntity.setNombre_ac(actividadDTO.getNombre_ac());
		return actividadEntity;
		
	}
	public static List<ActividadEntity> listActividadDTOToListActividadEntity(List<ActividadDTO> listActividadDTO){
		 List<ActividadEntity> listActividadEntity = new ArrayList<>();
		 for (ActividadDTO actividadDTO : listActividadDTO) {
			 listActividadEntity.add(actividadDTOToActividadEntity(actividadDTO));
		 }
		 return listActividadEntity;
		 
		 
	}
	public static FechaActividadEntity fechaactividadDTOToFechaActividadEntity(FechaActividadDTO fechaActividadDTO ) throws ParseException {
		FechaActividadEntity fechaActividadEntity = new FechaActividadEntity();
		//DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss ZZZZZ", Locale.GERMANY);
		fechaActividadEntity.setFecha(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ROOT).parse(fechaActividadDTO.getFecha()));
		fechaActividadEntity.setNombre_ac(fechaActividadDTO.getNombre_act());
		return fechaActividadEntity;
		
	}
	public static List<FechaActividadEntity> fechaactividadDTOListToFechaActividadEntityList(List<FechaActividadDTO> listfechaActividadDTO ) throws ParseException {
		List<FechaActividadEntity> listFechaActividadEntity = new ArrayList<>();
		for (FechaActividadDTO fechaActividadDTO : listfechaActividadDTO) {
			listFechaActividadEntity.add(fechaactividadDTOToFechaActividadEntity(fechaActividadDTO));
		}
		return listFechaActividadEntity;
		
	}
	
	public static FechaActividadDTO fechaActividadEntityToFechaactividadDTO(FechaActividadEntity fechaActividadEntity ) {
		FechaActividadDTO fechaActividadDTO = new FechaActividadDTO();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		
		fechaActividadDTO.setFecha(dateFormat.format( fechaActividadEntity.getFecha()));
		fechaActividadDTO.setNombre_act(fechaActividadEntity.getNombre_ac());
		return fechaActividadDTO;
		/*Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		String strDate = dateFormat.format(date); */
		
	}
	public static List<FechaActividadDTO> fechaactividadEntityListToFechaActividadDTOList(List<FechaActividadEntity> listfechaActividadEntity ) {
		List<FechaActividadDTO> listFechaActividadDTO = new ArrayList<>();
		for (FechaActividadEntity fechaActividadEntity : listfechaActividadEntity) {
			listFechaActividadDTO.add(fechaActividadEntityToFechaactividadDTO(fechaActividadEntity));
		}
		return listFechaActividadDTO;
	}
	
	public static AltaActividadEntity altaActividadDTOToAltaActividadEntity (AltaActividadDTO altaActividadDTO) {
		AltaActividadEntity altaActividadEntity = new AltaActividadEntity();
		altaActividadEntity.setActividad(altaActividadDTO.getActividad());
		altaActividadEntity.setFecha_actividad(altaActividadDTO.getFecha_actividad());
		altaActividadEntity.setUsername(altaActividadDTO.getUsername());
		return altaActividadEntity;
	}
	public static List<AltaActividadEntity> altaActividadDTOListToAltaActividadEntityList(List<AltaActividadDTO> altaActividadList){
		List<AltaActividadEntity> altaActividadEntityList = new ArrayList<>();
		for (AltaActividadDTO altaActividadDTO :altaActividadList) {
			altaActividadEntityList.add(altaActividadDTOToAltaActividadEntity(altaActividadDTO));
		}
		return altaActividadEntityList ;
	}
	
	//CONVERTER ENTITY dAYmONTHyEAR A dto
	public static DayMonthYearDTO dayMonthYearEntityTodayMonthYearDTO(DayMonthYearEntity dayMonthYearEntity) {
		DayMonthYearDTO dayMonthYearDTO = new DayMonthYearDTO();
		dayMonthYearDTO.setYear(dayMonthYearEntity.getCompositeKeyDayMonthYear().getYear());
		dayMonthYearDTO.setMes(dayMonthYearEntity.getCompositeKeyDayMonthYear().getMes());
		dayMonthYearDTO.setActividad(dayMonthYearEntity.getCompositeKeyDayMonthYear().getActividad());
		dayMonthYearDTO.setLunes(dayMonthYearEntity.getLunes());
		dayMonthYearDTO.setMartes(dayMonthYearEntity.getMartes());
		dayMonthYearDTO.setMiercoles(dayMonthYearEntity.getMiercoles());
		dayMonthYearDTO.setJueves(dayMonthYearEntity.getJueves());
		dayMonthYearDTO.setViernes(dayMonthYearEntity.getViernes());	
			return dayMonthYearDTO;
	}
	public static DayMonthYearEntity dayMonthYearDTOTodayMonthYearEntity(DayMonthYearDTO dayMonthYearDTO) {
		DayMonthYearEntity dayMonthYearEntity = new DayMonthYearEntity();
		dayMonthYearEntity.setCompositeKeyDayMonthYear(new CompositeKeyDayMonthYear(dayMonthYearDTO.getYear(),dayMonthYearDTO.getMes(),dayMonthYearDTO.getActividad()));
		dayMonthYearEntity.setLunes(dayMonthYearDTO.getLunes());
		dayMonthYearEntity.setMartes(dayMonthYearDTO.getMartes());
		dayMonthYearEntity.setMiercoles(dayMonthYearDTO.getMiercoles());
		dayMonthYearEntity.setJueves(dayMonthYearDTO.getJueves());
		dayMonthYearEntity.setViernes(dayMonthYearDTO.getViernes());	
			return dayMonthYearEntity;
	}
	
	
	
	
}