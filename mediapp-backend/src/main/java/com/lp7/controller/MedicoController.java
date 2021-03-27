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

import com.lp7.exception.ModeloNotFoundException;
import com.lp7.model.Medico;
import com.lp7.service.IMedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private IMedicoService service;
	
	@GetMapping
	public ResponseEntity<List<Medico>> listar() throws Exception{
		List<Medico> lista = service.listar();
		return new ResponseEntity<List<Medico>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medico> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Medico medico = service.listarPorId(id);
		if(medico == null) {
			throw new ModeloNotFoundException("Medico con el ID: " + id + " no existe");
		}
		return new ResponseEntity<Medico>(medico, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Medico> registrar(@RequestBody Medico medico) throws Exception{
		Medico registro = service.registrar(medico);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registro.getId()).toUri();
		return  ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Medico> modificar(@PathVariable("id") Integer id, @RequestBody Medico medico) throws Exception{
		Medico medicoActual = service.listarPorId(id);
		if(medicoActual == null) {
			throw new ModeloNotFoundException("Medico con el ID; " + id + " no existe");
		}
		medicoActual.setNombres(medico.getNombres());
		medicoActual.setApellidos(medico.getApellidos());
		medicoActual.setCmp(medico.getCmp());
		medicoActual.setFotoUrl(medico.getFotoUrl());
		Medico medicoUpdate = service.modificar(medicoActual);
		return new ResponseEntity<Medico>(medicoUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Medico medico = service.listarPorId(id);
		if(medico == null) {
			throw new ModeloNotFoundException("Medico con el ID; " + id + " no existe");
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
