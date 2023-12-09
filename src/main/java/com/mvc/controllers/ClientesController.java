package com.mvc.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.mvc.services.ClientesService;
import com.mvc.models.ClientesModel;

@RestController
// Definición de la ruta de Cliente
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientesController {
	
	@Autowired
	ClientesService clientesService;
	
//	ResponseEntity devuelve una Response Ok (200)
	@GetMapping()
	public ResponseEntity<ArrayList<ClientesModel>> obtenerClientes(){
		return ResponseEntity.ok(clientesService.obtenerClientes());
	}
	
	@PostMapping()
//	RequestBody se utiliza para capturar los parámetros de la URL
	public ResponseEntity<Void> guardarCliente(@RequestBody ClientesModel cliente,  UriComponentsBuilder ucb) {
		ClientesModel savedCliente = this.clientesService.guardarCliente(cliente);
//		UriComponentsBuilder se utiliza para crear la URI/URL de respuesta una vez creado el registro
		URI locationOfNewCashCard = ucb
				.path("cliente/{id}")
				.buildAndExpand(savedCliente.getId())
				.toUri();
//		ResponseEntity devuelve una Response CREATED (201)
		return ResponseEntity.created(locationOfNewCashCard).build();
	}
	
	@GetMapping(path="/{id}")
//	PathVariable captura la variable de la uri
//	ResponseEntity devuelve una Response con el Cliente OK (200) o NOT FOUND (404)
	public ResponseEntity<Optional<ClientesModel>> obtenerClientePorId(@PathVariable("id") long id){
		Optional<ClientesModel> cliente = this.clientesService.obtenerPorId(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path="/query")
	public ArrayList<ClientesModel> obtenerClientesPorNombre(@RequestParam("nombre") String nombre){
		return this.clientesService.obtenerPorNombre(nombre);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.clientesService.eliminarCliente(id);
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
	public ResponseEntity<Void>  actualizarCliente(@PathVariable("id") long id, @RequestBody ClientesModel cambios) {
		boolean resultado = this.clientesService.actualizarCliente(id, cambios);
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
