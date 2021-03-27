package com.lp7.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lp7.dao.IGenericDao;
import com.lp7.service.ICRUDService;

public abstract class CRUDImpl<T, ID> implements ICRUDService<T, ID>{
	
	protected abstract IGenericDao<T, ID> getDao();

	@Override
	public List<T> listar() {
		return getDao().findAll();
	}

	@Override
	public T listarPorId(ID id) {
		return getDao().findById(id).orElse(null);
	}

	@Transactional
	@Override
	public T registrar(T t) {
		return getDao().save(t);
	}

	@Transactional
	@Override
	public T modificar(T t) {
		return getDao().save(t);
	}

	@Transactional
	@Override
	public void eliminar(ID id) {
		getDao().deleteById(id);
	}

}
