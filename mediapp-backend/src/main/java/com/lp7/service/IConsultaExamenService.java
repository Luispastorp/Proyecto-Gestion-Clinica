package com.lp7.service;

import java.util.List;

import com.lp7.model.ConsultaExamen;

public interface IConsultaExamenService {

	List<ConsultaExamen> listarExamenesPorConsulta(Integer idConsulta);
	
}
