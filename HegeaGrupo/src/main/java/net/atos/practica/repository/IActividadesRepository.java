package net.atos.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.atos.practica.entity.ActividadEntity;



public interface IActividadesRepository extends JpaRepository<ActividadEntity, Long>{

}
