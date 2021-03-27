package com.lp7.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp7.dao.IEspecialidadDao;
import com.lp7.dao.IGenericDao;
import com.lp7.model.Especialidad;
import com.lp7.service.IEspecialidadService;

@Service
public class EspecialidadServiceImpl extends CRUDImpl<Especialidad, Integer> implements IEspecialidadService{

	@Autowired
	private IEspecialidadDao dao;
	
	@Override
	protected IGenericDao<Especialidad, Integer> getDao() {
		return dao;
	}

}
