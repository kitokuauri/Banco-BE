package com.mvc.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mvc.models.GestoresModel;
import com.mvc.services.GestoresService;

@RestController
@RequestMapping("/gestor")
@CrossOrigin(origins = "http://localhost:4200")
public class GestoresController {

	@Autowired
	GestoresService gestoresService;
	
	@GetMapping()
	public ResponseEntity<ArrayList<GestoresModel>> obtenerGestores(){
		return ResponseEntity.ok(gestoresService.obtenerGestores());
	}
	
	@PostMapping()
//	RequestBody se utiliza para capturar los parámetros de la URL
	public ResponseEntity<Void> guardarGestor(@RequestBody GestoresModel gestor, UriComponentsBuilder ucb) {
		GestoresModel savedGestor = this.gestoresService.guardarGestor(gestor);
//		UriComponentsBuilder se utiliza para crear la URI/URL de respuesta una vez creado el registro
		URI locationOfNewCashCard = ucb
				.path("gestor/{id}")
				.buildAndExpand(savedGestor.getId())
				.toUri();
//		ResponseEntity devuelve una Response CREATED (201)
		return ResponseEntity.created(locationOfNewCashCard).build();
	}
	
	@GetMapping(path="/{id}")
//	PathVariable captura la variable de la uri
	public ResponseEntity<Optional<GestoresModel>> obtenerGestorPorId(@PathVariable("id") long id){
		Optional<GestoresModel> gestor = this.gestoresService.obtenerPorId(id);
		if (gestor.isPresent()) {
			return ResponseEntity.ok(gestor);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path="/query")
	public ArrayList<GestoresModel> obtenerGestorPorNombre(@RequestParam("nombre") String nombre){
		if(nombre == "") {
			return new ArrayList<GestoresModel>();
		}
		return this.gestoresService.obtenerPorNombre(nombre);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.gestoresService.eliminarGestor(id);
		if(resultado) {
//			devuelve una respuesta Ok vacía
			return ResponseEntity.noContent().build();	
		} else {
//			devuelve una respuesta de página no encontrada.
//			En la operación de borrado indica que la operación no tuvo éxito
			return ResponseEntity.notFound().build();
		}
	}
	
	@PatchMapping(path = "/{id}")
	public ResponseEntity<Void> actualizarGestor(@PathVariable("id") long id, @RequestBody GestoresModel cambios) {
		boolean resultado = this.gestoresService.actualizarGestor(id, cambios);
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
