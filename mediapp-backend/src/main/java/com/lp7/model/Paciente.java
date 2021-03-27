package com.lp7.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pacientes")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min = 3, message = "Nombre minimo 3 caracteres")
	@Column(nullable = false, length = 70)
	private String nombres;
	
	@NotNull
	@Size(min = 3, message = "Apellidos minimo 3 caracteres")
	@Column(nullable = false, length = 70)
	private String apellidos;
	
	@NotNull
	@Size(min = 8, max = 8, message = "DNI debe tener 8 caracteres")
	@Column(nullable = false, length = 8, unique = true)
	private String dni;
	
	@Size(min = 3, message = "El DNI debe tener 8 caracteres")
	@Column(nullable = false, length = 150)
	private String direccion;
	
	@Size(min = 9, max = 9, message = "Telefono debe tener 8 caracteres")
	@Column(length = 9)
	private String telefono;
	
	@Email
	@Column(length = 70)
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
