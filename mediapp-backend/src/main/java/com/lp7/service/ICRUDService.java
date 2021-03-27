package com.lp7.service;

import java.util.List;

public interface ICRUDService<T, ID> {
	
	List<T> listar() throws Exception;
	T listarPorId(ID id) throws Exception;
	T registrar(T t) throws Exception;
	T modificar(T t) throws Exception;
	void eliminar(ID id) throws Exception;

}
