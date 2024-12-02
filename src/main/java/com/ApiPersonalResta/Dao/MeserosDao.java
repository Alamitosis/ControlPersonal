package com.ApiPersonalResta.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ApiPersonalResta.Models.Meseros;

public interface MeserosDao extends CrudRepository<Meseros, Long>{

	public boolean existsByNombreAndApellidoPAndApellidoM(String nombre, String apellidoP, String apellidoM);
	public boolean existsById(Long id);
}
