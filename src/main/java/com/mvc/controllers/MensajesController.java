package com.mvc.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.mvc.models.ClientesModel;
import com.mvc.models.MensajesModel;
import com.mvc.services.MensajesService;

@RestController
@RequestMapping("/mensaje")
@CrossOrigin(origins = "http://localhost:4200")
public class MensajesController {

	@Autowired
	MensajesService mensajesService;
	
	@GetMapping()
	public ResponseEntity<ArrayList<MensajesModel>> obtenerMensajes(){
		return ResponseEntity.ok(mensajesService.obtenerMensajes());
	}
	
	@PostMapping()
//	RequestBody se utiliza para capturar los parámetros de la URL
	public ResponseEntity<Void> guardarMensaje(@RequestBody MensajesModel mensaje, UriComponentsBuilder ucb) {
		MensajesModel savedMensaje = this.mensajesService.guardarMensaje(mensaje);
//		UriComponentsBuilder se utiliza para crear la URI/URL de respuesta una vez creado el registro
		URI locationOfNewCashCard = ucb
				.path("cliente/{id}")
				.buildAndExpand(savedMensaje.getId())
				.toUri();
//		ResponseEntity devuelve una Response CREATED (201)
		return ResponseEntity.created(locationOfNewCashCard).build();
	}
	
	@GetMapping(path="/{id}")
//	PathVariable captura la variable de la uri
	public ResponseEntity<Optional<MensajesModel>> obtenerMensajePorId(@PathVariable("id") long id){
		Optional<MensajesModel> mensaje = this.mensajesService.obtenerPorId(id);
		if (mensaje.isPresent()) {
			return ResponseEntity.ok(mensaje);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path="/r_query")
	public ArrayList<MensajesModel> obtenerMensajePorIdRemitente(@RequestParam("id_remitente") ClientesModel id_remitente){
		return this.mensajesService.obtenerPorIdRemitente(id_remitente);
	}
	
	@GetMapping(path="/d_query")
	public ArrayList<MensajesModel> obtenerMensajePorIdDestinatario(@RequestParam("id_destinatario") ClientesModel id_destinatario){
		return this.mensajesService.obtenerPorIdDestinatario(id_destinatario);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.mensajesService.eliminarMensaje(id);
		if(resultado) {
//			devuelve una respuesta Ok vacía
			return ResponseEntity.noContent().build();	
		} else {
//			devuelve una respuesta de página no encontrada.
//			En la operación de borrado indica que la operación no tuvo éxito
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
}
