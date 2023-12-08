package com.mvc.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mvc.models.MensajesModel;
import com.mvc.services.MensajesService;

@RestController
@RequestMapping("/mensaje")
@CrossOrigin(origins = "http://localhost:4200")
public class MensajesController {

	@Autowired
	MensajesService mensajesService;
	
	@GetMapping()
	public ArrayList<MensajesModel> obtenerMensajes(){
		return mensajesService.obtenerMensajes();
	}
	
	@PostMapping()
//	RequestBody se utiliza para capturar los parámetros de la URL
	public MensajesModel guardarMensaje(@RequestBody MensajesModel mensaje) {
		return this.mensajesService.guardarMensaje(mensaje);
	}
	
	@GetMapping(path="/{id}")
//	PathVariable captura la variable de la uri
	public Optional<MensajesModel> obtenerMensajePorId(@PathVariable("id") long id){
		return this.mensajesService.obtenerPorId(id);
	}
	
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.mensajesService.eliminarMensaje(id);
		if(resultado) {
			return "Se eliminó el usuario con id " + id;
		} else {
			return "No se pudo eliminar el usuario con id " + id;
		}
	}
	
	@PatchMapping(path = "/{id}")
	public MensajesModel actualizarMensaje(@PathVariable("id") long id, @RequestBody Map<String, Object> cambios) {
		return this.mensajesService.actualizarMensaje(id, cambios);
	}
	
	
}
