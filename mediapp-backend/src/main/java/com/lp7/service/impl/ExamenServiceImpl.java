package com.lp7.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp7.dao.IExamenDao;
import com.lp7.dao.IGenericDao;
import com.lp7.model.Examen;
import com.lp7.service.IExamenService;

@Service
public class ExamenServiceImpl extends CRUDImpl<Examen, Integer> implements IExamenService{

	@Autowired
	private IExamenDao dao;
	
	@Override
	protected IGenericDao<Examen, Integer> getDao() {
		return dao;
	}

}
