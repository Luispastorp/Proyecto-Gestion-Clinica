package com.lp7.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lp7.dao.IConsultaDao;
import com.lp7.dao.IConsultaExamenDao;
import com.lp7.dao.IGenericDao;
import com.lp7.dto.ConsultaListaExamenDTO;
import com.lp7.dto.FiltroConsultaDTO;
import com.lp7.model.Consulta;
import com.lp7.service.IConsultaService;

@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta, Integer> implements IConsultaService{

	@Autowired
	private IConsultaDao dao;
	
	@Autowired
	private IConsultaExamenDao ceDao;
	
	@Override
	protected IGenericDao<Consulta, Integer> getDao() {
		return dao;
	}

	@Transactional
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) throws Exception {
		dto.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(dto.getConsulta()));
		dao.save(dto.getConsulta());
		dto.getExamenes().forEach(ex -> ceDao.registrar(dto.getConsulta().getId(),  ex.getId()));
		return dto.getConsulta();
	}

	@Override
	public List<Consulta> buscar(FiltroConsultaDTO filtro) {
		return dao.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarFecha(LocalDateTime fecha) {
		return dao.buscarFecha(fecha, fecha.plusDays(1));
	}

}
