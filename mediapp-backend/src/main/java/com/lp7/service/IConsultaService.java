package com.lp7.service;

import com.lp7.dto.ConsultaListaExamenDTO;
import com.lp7.model.Consulta;

public interface IConsultaService extends ICRUDService<Consulta, Integer>{

	Consulta registrarTransaccional(ConsultaListaExamenDTO dto) throws Exception;
}
