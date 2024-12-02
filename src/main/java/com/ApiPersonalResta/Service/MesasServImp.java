package com.ApiPersonalResta.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ApiPersonalResta.Dao.MesasDao;
import com.ApiPersonalResta.Dao.MeserosDao;
import com.ApiPersonalResta.Models.Mesas;
import com.ApiPersonalResta.Models.Meseros;

@Service
public class MesasServImp {

	@Autowired
	MesasDao mesasDao;
	
	@Autowired
	MeserosDao meserDao;
	
	@Transactional(readOnly = true)
	public List<Mesas> listar() {
		return (List<Mesas>) mesasDao.findAll();
	}
	
	@Transactional
	public Mesas buscarId(Long id) {
		return mesasDao.findById(id).orElse(null);
	}
	
	//VALIDAR ID Y NUM-MESA QUE NO SE REPITA Y ID_MESERO EXISTA
	@Transactional
	public String guardar(Mesas mesa) {
		String respuesta="";
		Boolean banderaIdMesero=false;
		Boolean banderaId=false;
		Boolean banderaNumMesa=false;
		
		for(Meseros meser: meserDao.findAll()) {
			if(meser.getId().equals(mesa.getMesero().getId())) {
				banderaIdMesero=true;
				
				for(Mesas m:mesasDao.findAll()) {
					if(m.getId().equals(mesa.getId())) {
						banderaId=true;
					}
					else if(m.getNumMesa().equals(mesa.getNumMesa())) {
						banderaNumMesa=true;
					}
				}
			}
		}
		
		if((banderaIdMesero==true)&&(banderaId==false)&&(banderaNumMesa==false)) {
			mesasDao.save(mesa);
			respuesta="guardado";
		}
		else if(banderaIdMesero==false)
			respuesta="MeseroNoExiste";
		else if(banderaId==true)
			respuesta="IdMesaYaExiste";
		else if(banderaNumMesa==true)
			respuesta="NumMesaYaExiste";
		return respuesta;
	}
	
	//VALIDARQUE EL ID Y EL ID_MESERO EXISTA
	@Transactional
	public String editar(Mesas mesa) {
		String respuesta="";
		Boolean banderaId=false;
		Boolean banderaIdMesero=false;
		
		for(Meseros meser:meserDao.findAll()) {
			if(meser.getId().equals(mesa.getMesero().getId())) {
				banderaIdMesero=true;
				for(Mesas m:mesasDao.findAll()) {
					if(m.getId().equals(mesa.getId())) {
						banderaId=true;
					}
				}
			}
		}
		
		if((banderaId==true)&&(banderaIdMesero==true))
			respuesta="editado";
		else if(banderaId==false)
			respuesta="NoIdMesa";
		else if(banderaIdMesero==false)
			respuesta="NoIdMesero";
		return respuesta;
	}
	
	//VALIDAR QUE EL ID EXISTA
	@Transactional
	public Boolean eliminar(Mesas mesa) {
		Boolean banderaId=false;
		if (mesasDao.existsById(mesa.getId())) {
			mesasDao.deleteById(mesa.getId());;
			banderaId=true;
		}
		
		return banderaId;
	}
}
