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
	
	@GetMapping(path="/r_query")
	public ArrayList<MensajesModel> obtenerMensajePorRemitente(@RequestParam("remitente") String remitente){
		return this.mensajesService.obtenerPorRemitente(remitente);
	}
	
	@GetMapping(path="/d_query")
	public ArrayList<MensajesModel> obtenerMensajePorDestinatario(@RequestParam("destinatario") String destinatario){
		return this.mensajesService.obtenerPorDestinatario(destinatario);
	}
	
	@DeleteMapping(path="/{id}")
	public void eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.mensajesService.eliminarMensaje(id);
		if(!resultado) {
			System.out.println("No se pudo eliminar el mensaje");
		}
	}
	
	@PatchMapping(path = "/{id}")
	public void actualizarMensaje(@PathVariable("id") long id, @RequestBody Map<String, Object> cambios) {
		boolean resultado = this.mensajesService.actualizarMensaje(id, cambios);
		if(!resultado) {
			System.out.println("No se pudo eliminar el mensaje");
		}
	}
	
	
}
