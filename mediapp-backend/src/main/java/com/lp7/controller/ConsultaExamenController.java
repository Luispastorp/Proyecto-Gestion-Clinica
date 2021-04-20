package com.lp7.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp7.exception.ModeloNotFoundException;
import com.lp7.model.ConsultaExamen;
import com.lp7.service.IConsultaExamenService;

@RestController
@RequestMapping("/consultasExamenes")
public class ConsultaExamenController {
	
	@Autowired
	private IConsultaExamenService service;
	
	@GetMapping("/{idConsulta}")
	public ResponseEntity<List<ConsultaExamen>> listar(@PathVariable("idConsulta") Integer idConsulta){
		List<ConsultaExamen> lista = new ArrayList<>();
		lista = service.listarExamenesPorConsulta(idConsulta);
		if(lista.isEmpty()) {
			throw new ModeloNotFoundException("No hay examenes en esta consulta");
		}
		return new ResponseEntity<List<ConsultaExamen>>(lista, HttpStatus.OK);
	}

}
