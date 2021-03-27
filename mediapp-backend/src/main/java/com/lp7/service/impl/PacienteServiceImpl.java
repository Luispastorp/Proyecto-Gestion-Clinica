package com.lp7.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp7.dao.IGenericDao;
import com.lp7.dao.IPacienteDao;
import com.lp7.model.Paciente;
import com.lp7.service.IPacienteService;

@Service
public class PacienteServiceImpl extends CRUDImpl<Paciente, Integer> implements IPacienteService{

	@Autowired
	private IPacienteDao dao;

	@Override
	protected IGenericDao<Paciente, Integer> getDao() {
		return dao;
	}
	
}
