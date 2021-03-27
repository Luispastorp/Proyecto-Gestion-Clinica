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
import com.lp7.model.Examen;
import com.lp7.service.IExamenService;

@RestController
@RequestMapping("/examenes")
public class ExamenController {
	
	@Autowired
	private IExamenService service;
	
	@GetMapping
	public ResponseEntity<List<Examen>> listar() throws Exception{
		List<Examen> lista = service.listar();
		return new ResponseEntity<List<Examen>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Examen> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Examen examen = service.listarPorId(id);
		if(examen == null) {
			throw new ModeloNotFoundException("Examen con el ID: " + id + " no existe");
		}
		return new ResponseEntity<Examen>(examen, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Examen> registrar(@RequestBody Examen examen) throws Exception{
		Examen registro = service.registrar(examen);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registro.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Examen> modificar(@PathVariable("id") Integer id, @RequestBody Examen examen) throws Exception{
		Examen examenActual = service.listarPorId(id);
		if(examenActual == null) {
			throw new ModeloNotFoundException("Examen con el ID: " + id + " no existe");
		}
		examenActual.setNombre(examen.getNombre());
		examenActual.setDescripcion(examen.getDescripcion());
		Examen examenUpdate = service.modificar(examenActual);
		return new ResponseEntity<Examen>(examenUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception{
		Examen examen= service.listarPorId(id);
		if(examen == null) {
			throw new ModeloNotFoundException("Examen con el ID: " + id + " no existe");
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
