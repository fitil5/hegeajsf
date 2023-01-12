package net.atos.practica.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.atos.practica.entity.OrganizacionEntity;



public interface IOrganizacionesRepository extends JpaRepository<OrganizacionEntity, Long>{
	public List<OrganizacionEntity> findAll();
}
