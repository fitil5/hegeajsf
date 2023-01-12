package net.atos.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import net.atos.practica.entity.CompositeKeyDayMonthYear;
import net.atos.practica.entity.DayMonthYearEntity;


public interface IDayMonthYearRepository extends JpaRepository<DayMonthYearEntity,CompositeKeyDayMonthYear>{
public DayMonthYearEntity findByCompositeKeyDayMonthYear(CompositeKeyDayMonthYear compositeKeyDayMonthYear);

}
