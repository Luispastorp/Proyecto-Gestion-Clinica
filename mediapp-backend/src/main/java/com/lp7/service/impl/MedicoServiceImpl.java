package com.lp7.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp7.dao.IGenericDao;
import com.lp7.dao.IMedicoDao;
import com.lp7.model.Medico;
import com.lp7.service.IMedicoService;

@Service
public class MedicoServiceImpl extends CRUDImpl<Medico, Integer> implements IMedicoService{
	
	@Autowired
	private IMedicoDao dao;

	@Override
	protected IGenericDao<Medico, Integer> getDao() {
		return dao;
	}

}
