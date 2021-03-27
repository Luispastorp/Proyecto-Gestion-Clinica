package com.lp7.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import com.lp7.model.Paciente;
import com.lp7.service.IPacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private IPacienteService service;
	
	@GetMapping
	public ResponseEntity<?> listar() throws Exception{
		List<Paciente> lista = service.listar();		
		return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Paciente paciente = null;
		paciente = service.listarPorId(id);
		if(paciente == null) {			
			throw new ModeloNotFoundException("Paciente con el Id: " + id + " no existe");
		}
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> registar(@Valid @RequestBody Paciente paciente) throws Exception{
		Paciente pac = service.registrar(paciente);;
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@PathVariable("id") Integer id,@Valid @RequestBody Paciente paciente) throws Exception{
		Paciente pacienteActual = service.listarPorId(id);		
		if(pacienteActual == null) {
			throw new ModeloNotFoundException("Paciente con el Id: " + id + " no existe");
		}
		pacienteActual.setNombres(paciente.getNombres());
		pacienteActual.setApellidos(paciente.getApellidos());
		pacienteActual.setDireccion(paciente.getDireccion());
		pacienteActual.setDni(paciente.getDni());
		pacienteActual.setEmail(paciente.getEmail());
		pacienteActual.setTelefono(paciente.getTelefono());
		Paciente pacienteUpdate = service.modificar(pacienteActual);
		return new ResponseEntity<Paciente>(pacienteUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) throws Exception{
		Paciente paciente = service.listarPorId(id);
		if(paciente == null) {
			throw new ModeloNotFoundException("Paciente con el Id: " + id + " no existe");
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
	
	
	
