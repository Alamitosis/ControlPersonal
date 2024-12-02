package com.ApiPersonalResta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiPersonalResta.Models.Meseros;
import com.ApiPersonalResta.Service.MeserosServImp;

@RestController
@RequestMapping(path="MeserosWS")
@CrossOrigin
public class MeserosWS {

	@Autowired
	MeserosServImp imp;
	
	//http://localhost:9000/MeserosWS/mostrar
	@GetMapping(path="mostrar")
	public List<Meseros> mostrar(){
		return imp.mostrar();
	}
	
	//http://localhost:9000/MeserosWS/buscar
	@PostMapping(path="buscar")
	public Meseros buscar(@RequestBody Meseros mesero) {
		return imp.buscar(mesero.getId());
	}
	
	//http://localhost:9000/MeserosWS/guardar
	@PostMapping(path="guardar")
	public ResponseEntity<?> guardar(@RequestBody Meseros mesero) {
		String respuesta=imp.guardar(mesero);
		
		if(respuesta.equals("guardado"))
			return new ResponseEntity<>("Guardado realizado correctamente",HttpStatus.OK);
		else if(respuesta.equals("IdExistente"))
			return new ResponseEntity<>("El id de este mesero ya existe",HttpStatus.OK);
		else if(respuesta.equals("NombreExistente"))
			return new ResponseEntity<>("El nombre completo de este mesero ya está registrado",HttpStatus.OK);
		else
			return new ResponseEntity<>("Error desconocido",HttpStatus.OK);
	}
	
	//http://localhost:9000/MeserosWS/editar
	@PostMapping(path="editar")
	public ResponseEntity<?> editar(@RequestBody Meseros mesero) {
		String respuesta=imp.editar(mesero);
		if(respuesta.equals("editado"))
			return new ResponseEntity<>("Se editó correctamente",HttpStatus.OK);
		else if(respuesta.equals("NoId"))
			return new ResponseEntity<>("El id no se encontró",HttpStatus.OK);
		else
			return new ResponseEntity<>("Error desconocido",HttpStatus.OK);

	}
	
	//http://localhost:9000/MeserosWS/eliminar
	@PostMapping(path="eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Meseros mesero) {
		String respuesta=imp.eliminar(mesero);
		if(respuesta.equals("eliminado"))
			return new ResponseEntity<>("Se eliminó correctamente",HttpStatus.OK);
		else if(respuesta.equals("NoId"))
			return new ResponseEntity<>("El id no se encontró",HttpStatus.OK);
		else
			return new ResponseEntity<>("Error desconocido",HttpStatus.OK);
	}
}
