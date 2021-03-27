package com.lp7.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lp7.dto.ConsultaListaExamenDTO;
import com.lp7.exception.ModeloNotFoundException;
import com.lp7.model.Consulta;
import com.lp7.service.IConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	
	@Autowired
	private IConsultaService service;
	
	@GetMapping
	public ResponseEntity<List<Consulta>> listar() throws Exception{
		List<Consulta> lista = service.listar();
		return new ResponseEntity<List<Consulta>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Consulta> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Consulta consulta = service.listarPorId(id);
		if(consulta == null) {
			throw new ModeloNotFoundException("Examen con el ID: " + id + " no existe");
		}
		return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Consulta> registrar(@RequestBody ConsultaListaExamenDTO dto) throws Exception{
		Consulta registro = service.registrarTransaccional(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registro.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Consulta> modificar(@PathVariable("id") Integer id, @RequestBody Consulta consulta) throws Exception{
		Consulta consultaActual = service.listarPorId(id);
		if(consultaActual == null) {
			throw new ModeloNotFoundException("Consulta con el ID: " + id + " no existe");
		}
		consultaActual.setNombre(consulta.getNombre());
		consultaActual.setNumConsultorio(consulta.getNumConsultorio());
		consultaActual.setEspecialidad(consulta.getEspecialidad());
		consultaActual.setMedico(consulta.getMedico());
		consultaActual.setPaciente(consulta.getPaciente());
		Consulta consultaUpdate = service.modificar(consultaActual);
		return new ResponseEntity<Consulta>(consultaUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception{
		Consulta consulta= service.listarPorId(id);
		if(consulta == null) {
			throw new ModeloNotFoundException("Consulta con el ID: " + id + " no existe");
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
