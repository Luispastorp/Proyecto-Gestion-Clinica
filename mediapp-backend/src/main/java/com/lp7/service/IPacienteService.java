package com.lp7.service;

import java.util.List;

import com.lp7.model.Paciente;

public interface IPacienteService {
	
	List<Paciente> listar();
	Paciente listarPorId(Integer id);
	Paciente registrar(Paciente paciente);
	Paciente modificar(Paciente paciente);
	void eliminar(Integer id);
	

}
