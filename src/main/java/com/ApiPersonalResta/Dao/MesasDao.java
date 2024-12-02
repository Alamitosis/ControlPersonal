package com.ApiPersonalResta.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ApiPersonalResta.Models.Mesas;

public interface MesasDao extends CrudRepository<Mesas, Long>{


}
