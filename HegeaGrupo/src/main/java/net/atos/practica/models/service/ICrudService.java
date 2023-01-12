package net.atos.practica.models.service;


import java.util.List;


import net.atos.practica.utils.dto.ActividadDTO;
import net.atos.practica.utils.dto.AltaActividadDTO;
import net.atos.practica.utils.dto.DayMonthYearDTO;
import net.atos.practica.utils.dto.FechaActividadDTO;
import net.atos.practica.utils.dto.OrganizacionDTO;
import net.atos.practica.utils.dto.ProyectoDTO;
import net.atos.practica.utils.dto.UsuarioDTO;

public interface ICrudService {

	public List<UsuarioDTO> Read();
	public void asignarDiasSemana(DayMonthYearDTO dayMonthYearDTO);
	public void createUser(UsuarioDTO newUsuario);
	public DayMonthYearDTO findByDayMonthActividades(int year,String mes,String actividad);
	public void deleteActividad(List<ActividadDTO> listActividadDTO);
	public void deleteUsuario(List<UsuarioDTO> usuarioList) ;
	public void deleteProyecto(List<ProyectoDTO> proyectoList);
	public List<String> findProyectosCrudController();
	public void createAltaActividadUsuario(AltaActividadDTO altaActividadDTO );
	public List<String> findOrganizaciones();
	public List<OrganizacionDTO> findOrganizacionesDTO();
	public List<ProyectoDTO> findProyectos();
	public void createProyecto(ProyectoDTO newProyecto);
	public void deleteOrganizacion (List<OrganizacionDTO> organizacionList);
	public void createOrganizacion(OrganizacionDTO newOrganizacion) ;
	public List<ActividadDTO> findActividades();
	public void createActividad(ActividadDTO newActividad);
	public void createFechaActividad(FechaActividadDTO newFechaActividad);
	public Boolean comprobarFechaActividad(String actividad,String fecha   );
	public List<FechaActividadDTO> findFechaActividades() ;
}
