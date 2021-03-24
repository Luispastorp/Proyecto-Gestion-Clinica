package com.lp7.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.lp7.model.Paciente;
import com.lp7.service.IPacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	IPacienteService service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		Map<String, Object> response = new  HashMap<>();
		List<Paciente> lista = null;
		try {
			lista = service.listar();
		}catch(DataAccessException e) {
			response.put("mensaje", "error al listar pacientes en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable("id") Integer id){
		Map<String, Object> response = new  HashMap<>();
		Paciente paciente = null;
		try {
			paciente = service.listarPorId(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "error al buscar paciente en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(paciente == null) {
			response.put("mensaje", "Paciente con el Id: " + id + " no existe");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> registar(@RequestBody Paciente paciente){
		Map<String, Object> response = new HashMap<>();
		Paciente pac = null;
		try {
			pac = service.registrar(paciente);
		}catch(DataAccessException e) {
			response.put("mensaje", "error al registrar paciente en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Paciente>(pac, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@PathVariable("id") Integer id, @RequestBody Paciente paciente){
		Map<String, Object> response = new  HashMap<>();
		Paciente pacienteActual = service.listarPorId(id);
		Paciente pacienteUpdate = null;
		if(pacienteActual == null) {
			response.put("mensaje", "Paciente con el Id: " + id + " no existe");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			pacienteActual.setNombres(paciente.getNombres());
			pacienteActual.setApellidos(paciente.getApellidos());
			pacienteActual.setDireccion(paciente.getDireccion());
			pacienteActual.setDni(paciente.getDni());
			pacienteActual.setEmail(paciente.getEmail());
			pacienteActual.setTelefono(paciente.getTelefono());
			pacienteUpdate = service.modificar(pacienteActual);
		}catch(DataAccessException e) {
			response.put("mensaje", "error al buscar paciente en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Paciente>(pacienteUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id){
		Paciente paciente = service.listarPorId(id);
		Map<String, Object> response = new HashMap<>();
		if(paciente == null) {
			response.put("mensaje", "Paciente con el Id: " + id + " no existe");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			service.eliminar(id);
		}catch(DataAccessException e){
			response.put("mensaje", "error al eliminar paciente en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
	
	
	
