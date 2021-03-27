package com.lp7.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "menus")
public class Menu {
	
	@Id
	private Integer id;
	
	@Column(length = 20)
	private String icono;
	
	@Column(length = 20)
	private String nombre;

	@Column(length = 150)
	private String url;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "menus_roles", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "rol_id"})})
	private List<Rol> roles;

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
