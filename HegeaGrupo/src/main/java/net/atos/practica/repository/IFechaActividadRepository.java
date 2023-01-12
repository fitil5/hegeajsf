package net.atos.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import net.atos.practica.entity.CompositeKey;
import net.atos.practica.entity.FechaActividadEntity;

public interface IFechaActividadRepository  extends JpaRepository<FechaActividadEntity,CompositeKey>{

}
