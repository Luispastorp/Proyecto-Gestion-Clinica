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
import com.lp7.model.Especialidad;
import com.lp7.service.IEspecialidadService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

	@Autowired
	private IEspecialidadService service;
	
	@GetMapping
	public ResponseEntity<?> listar() throws Exception{
		List<Especialidad> lista = service.listar();
		return new ResponseEntity<List<Especialidad>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Especialidad especialidad = service.listarPorId(id);
		if(especialidad == null) {
			throw new ModeloNotFoundException("Especialidad con el ID: " + id + " no existe");
		}
		return new ResponseEntity<Especialidad>(especialidad, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody Especialidad especialidad) throws Exception{
		Especialidad registro = service.registrar(especialidad);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registro.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@PathVariable("id") Integer id, @RequestBody Especialidad especialidad) throws Exception{
		Especialidad especialidadActual = service.listarPorId(id);
		if(especialidadActual == null) {
			throw new ModeloNotFoundException("Especialidad con el ID: " + id + " no existe");
		}
		especialidadActual.setDescripcion(especialidad.getDescripcion());
		especialidadActual.setNombre(especialidad.getNombre());
		Especialidad especialidadUpdate = service.modificar(especialidadActual);
		return new ResponseEntity<Especialidad>(especialidadUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) throws Exception{
		Especialidad especialidad = service.listarPorId(id);
		if(especialidad == null) {
			throw new ModeloNotFoundException("Especialidad con el ID: " + id + " no existe");
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
