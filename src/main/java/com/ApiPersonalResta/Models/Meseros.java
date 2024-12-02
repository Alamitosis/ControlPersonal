package com.ApiPersonalResta.Models;

import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="MESERO")
public class Meseros implements Serializable{

	/*
	 CREATE TABLE MESERO(
	ID NUMBER PRIMARY KEY,
	NOMBRE VARCHAR2(70) NOT NULL,
	APP VARCHAR2(70) NOT NULL,
	APM VARCHAR2(70),
	SUELDO NUMBER NOT NULL
	);
	 */
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOMBRE",columnDefinition = "VARCHAR2(70)", nullable = false)
	private String nombre;
	
	@Column(name="APP",columnDefinition = "VARCHAR2(70)", nullable = false)
	private String apellidoP;
	
	@Column(name="APM")
	private String apellidoM;
	
	@Column(name="SUELDO",columnDefinition = "NUMBER", nullable = false)
	private String sueldo;
	
    @OneToMany(mappedBy = "mesero", cascade = CascadeType.ALL)
    @JsonIgnore // Ignoramos la lista para evitar recursión infinita
    private List<Mesas> listaMesas = new ArrayList<>();


	public Meseros(Long id, String nombre, String apellidoP, String apellidoM, String sueldo) {
		this.id = id;
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.sueldo = sueldo;
	}

	public Meseros() {
	}

	@Override
	public String toString() {
		return "Meseros [id=" + id + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM
				+ ", sueldo=" + sueldo + "]\n";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoP() {
		return apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getApellidoM() {
		return apellidoM;
	}

	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}

	public String getSueldo() {
		return sueldo;
	}

	public void setSueldo(String sueldo) {
		this.sueldo = sueldo;
	}
	
	
}
