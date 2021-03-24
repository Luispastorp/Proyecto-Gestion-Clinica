package com.lp7.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lp7.model.Paciente;

public interface IPacienteDao extends JpaRepository<Paciente, Integer>{

}
