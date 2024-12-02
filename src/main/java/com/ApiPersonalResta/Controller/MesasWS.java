package com.ApiPersonalResta.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiPersonalResta.Models.Mesas;
import com.ApiPersonalResta.Service.MesasServImp;

@RestController
@RequestMapping(path="MesasWS")
@CrossOrigin
public class MesasWS {
	
	@Autowired
	MesasServImp imp;
	
	//http://localhost:9000/MesasWS/mostrar
	@GetMapping(path="mostrar")
	public List<Mesas> mostrar() {
		return imp.listar();
	}
	
	//http://localhost:9000/MesasWS/buscar
	@PostMapping(path="buscar")
	public Mesas buscar(@RequestBody Mesas mesa) {
		return imp.buscarId(mesa.getId());
	}
	
	//http://localhost:9000/MesasWS/guardar
	@PostMapping(path="guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesas mesa) {
		String respuesta=imp.guardar(mesa);
		
		if(respuesta.equals("guardado"))
			return new ResponseEntity<>("Mesa guardada con éxito",HttpStatus.OK);
		else if(respuesta.equals("MeseroNoExiste"))
			return new ResponseEntity<>("Este mesero no existe",HttpStatus.OK);
		else if(respuesta.equals("IdMesaYaExiste"))
			return new ResponseEntity<>("La mesa proporcionada ya se encuentra registrada",HttpStatus.OK);
		else if(respuesta.equals("NumMesaYaExiste"))
			return new ResponseEntity<>("El numero de mesa ya se encuentra registrado",HttpStatus.OK);
		else
			return new ResponseEntity<>("Error desconocido",HttpStatus.OK);
	}
	
	//http://localhost:9000/MesasWS/editar
	@PostMapping(path="editar")
	public ResponseEntity<?> editar(@RequestBody Mesas mesa) {
		String respuesta=imp.editar(mesa);
		
		if(respuesta.equals("editado"))
			return new ResponseEntity<>("Mesa editada con éxito",HttpStatus.OK);
		else if(respuesta.equals("NoIdMesa"))
			return new ResponseEntity<>("El id de la mesa no se encontró",HttpStatus.OK);
		else if(respuesta.equals("NoIdMesero"))
			return new ResponseEntity<>("El id del mesero no se encontró",HttpStatus.OK);
		else
			return new ResponseEntity<>("Error desconocido",HttpStatus.OK);
	}
	
	//http://localhost:9000/MesasWS/eliminar
	@PostMapping(path="eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Mesas mesa) {
		Boolean bandera=imp.eliminar(mesa);
		
		if(bandera==true)
			return new ResponseEntity<>("Mesa eliminada con éxito",HttpStatus.OK);
		else
			return new ResponseEntity<>("Esta mesa no existe",HttpStatus.OK);
	}

}
