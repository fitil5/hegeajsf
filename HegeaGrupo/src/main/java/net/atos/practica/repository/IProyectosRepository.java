package net.atos.practica.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.atos.practica.entity.ProyectoEntity;

public interface IProyectosRepository extends JpaRepository<ProyectoEntity, Long> {
	//public List<ProyectoEntity> findAll();
	//public void SaveProyecto(ProyectoEntity proyecetoEntity);
	
	public void delete(ProyectoEntity proyectoEntity);
}
