package com.ApiPersonalResta.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.ApiPersonalResta.Dao.MesasDao;
import com.ApiPersonalResta.Dao.MeserosDao;
import com.ApiPersonalResta.Models.Meseros;

@Service
public class MeserosServImp {

	@Autowired
	MeserosDao mesDao;
	
	@Transactional(readOnly = true)
	public List<Meseros> mostrar() {
		return (List<Meseros>) mesDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Meseros buscar(Long id) {
		return mesDao.findById(id).orElse(null);
	}
	
	//(VALIDAR QUE EL ID Y NOMBRECOMPLETO NO SE REPITA)
	@Transactional
	public String guardar(Meseros mesero) {
	    if (mesDao.existsById(mesero.getId())) {
	        return "IdExistente";
	    }
	    
	    if (mesDao.existsByNombreAndApellidoPAndApellidoM(mesero.getNombre(), mesero.getApellidoP(), mesero.getApellidoM())) {
	        return "NombreExistente"; 
	    }

	
	    mesDao.save(mesero);
	    return "guardado";
	}

	
	//(VALIDAR QUE EL ID EXISTA)
	@Transactional
	public String editar(Meseros mesero) {
		
		if(mesDao.existsById(mesero.getId())) {
			mesDao.save(mesero);
			return "editado";
		}
		else
			return"NoId";
	}
	
	//(VALIDAR QUE EL ID EXISTA)
	@Transactional
	public String eliminar(Meseros mesero) {
		if(mesDao.existsById(mesero.getId())) {
			mesDao.delete(mesero);
			return "eliminado";
		}
		else
			return"NoId";
	}
}
