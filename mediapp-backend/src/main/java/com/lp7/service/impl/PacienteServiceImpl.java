package com.lp7.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lp7.dao.IPacienteDao;
import com.lp7.model.Paciente;
import com.lp7.service.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService{

	@Autowired
	private IPacienteDao dao;

	@Override
	public List<Paciente> listar() {
		return dao.findAll();
	}

	@Override
	public Paciente listarPorId(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Paciente registrar(Paciente paciente) {
		return dao.save(paciente);
	}

	@Transactional
	@Override
	public Paciente modificar(Paciente paciente) {
		return dao.save(paciente);
	}

	@Transactional
	@Override
	public void eliminar(Integer id) {
		dao.deleteById(id);
	}
	
	
	
}
