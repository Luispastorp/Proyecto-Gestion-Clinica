package com.lp7.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp7.dao.IConsultaExamenDao;
import com.lp7.model.ConsultaExamen;
import com.lp7.service.IConsultaExamenService;

@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService{

	@Autowired
	private IConsultaExamenDao dao;
	
	@Override
	public List<ConsultaExamen> listarExamenesPorConsulta(Integer idConsulta) {
		return dao.listarExamenesPorConsulta(idConsulta);
	}
	
	

}
