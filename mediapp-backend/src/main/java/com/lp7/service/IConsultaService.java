package com.lp7.service;

import java.time.LocalDateTime;
import java.util.List;

import com.lp7.dto.ConsultaListaExamenDTO;
import com.lp7.dto.FiltroConsultaDTO;
import com.lp7.model.Consulta;

public interface IConsultaService extends ICRUDService<Consulta, Integer>{

	Consulta registrarTransaccional(ConsultaListaExamenDTO dto) throws Exception;
	
	List<Consulta> buscar(FiltroConsultaDTO filtro);
	
	List<Consulta>buscarFecha(LocalDateTime fecha);
}
