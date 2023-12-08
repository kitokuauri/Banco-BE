package com.mvc.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mvc.models.GestoresModel;
import com.mvc.services.GestoresService;

@RestController
@RequestMapping("/gestor")
@CrossOrigin(origins = "http://localhost:4200")
public class GestoresController {

	@Autowired
	GestoresService gestoresService;
	
	@GetMapping()
	public ArrayList<GestoresModel> obtenerGestores(){
		return gestoresService.obtenerGestores();
	}
	
	@PostMapping()
//	RequestBody se utiliza para capturar los parámetros de la URL
	public GestoresModel guardarGestor(@RequestBody GestoresModel gestor) {
		return this.gestoresService.guardarGestor(gestor);
	}
	
	@GetMapping(path="/{id}")
//	PathVariable captura la variable de la uri
	public Optional<GestoresModel> obtenerGestorPorId(@PathVariable("id") long id){
		return this.gestoresService.obtenerPorId(id);
	}
	
	@GetMapping(path="/query")
	public ArrayList<GestoresModel> obtenerGestorPorNombre(@RequestParam("nombre") String nombre){
		return this.gestoresService.obtenerPorNombre(nombre);
	}
	
	@DeleteMapping(path="/{id}")
	public void eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.gestoresService.eliminarGestor(id);
		if(!resultado) {
			System.out.println("No se pudo eliminar el gestor");
		}
	}
	
	@PatchMapping(path = "/{id}")
	public void actualizarGestor(@PathVariable("id") long id, @RequestBody Map<String, Object> cambios) {
		boolean resultado = this.gestoresService.actualizarGestor(id, cambios);
		
		if(!resultado) {
			System.out.println("No se pudo actualizar el gestor");
		}
	}
	
}
