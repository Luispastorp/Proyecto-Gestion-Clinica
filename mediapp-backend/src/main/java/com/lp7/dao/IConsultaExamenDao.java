package com.lp7.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lp7.model.ConsultaExamen;

public interface IConsultaExamenDao extends IGenericDao<ConsultaExamen, Integer>{
	
	@Modifying
	@Query(value = "INSERT INTO consultas_examenes(consulta_id, examen_id) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
	Integer registrar(@Param("idConsulta") Integer idConsulta, @Param("idExamen") Integer idExamen);

}
