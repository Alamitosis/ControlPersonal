package com.ApiPersonalResta.Models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="MESAS")
public class Mesas implements Serializable{

	/*
	CREATE TABLE MESAS(
	ID NUMBER PRIMARY KEY,
	NUM_MESA NUMBER NOT NULL,
	NUM_SILLAS NUMBER NOT NULL,
	ID_MESERO NUMBER NOT NULL,
	FOREIGN KEY(ID_MESERO) REFERENCES MESERO(ID)
	);
	 */
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="NUM_MESA",nullable = false)
	private Integer numMesa;
	
	@Column(name="NUM_SILLAS",nullable = false)
	private Integer numSillas;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MESERO")
    private Meseros mesero;

	public Mesas(Long id, Integer numMesa, Integer numSillas, Mesas mesa) {
		this.id = id;
		this.numMesa = numMesa;
		this.numSillas = numSillas;
		this.mesero = mesero;
	}

	public Mesas() {
	}

	@Override
	public String toString() {
		return "Mesas [id=" + id + ", numMesa=" + numMesa + ", numSillas=" + numSillas + ", mesero=" + mesero + "]\n";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(Integer numMesa) {
		this.numMesa = numMesa;
	}

	public Integer getNumSillas() {
		return numSillas;
	}

	public void setNumSillas(Integer numSillas) {
		this.numSillas = numSillas;
	}

	public Meseros getMesero() {
		return mesero;
	}

	public void setMesero(Meseros mesero) {
		this.mesero = mesero;
	}
	
	
}
