package com.mvc.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.mvc.models.ClientesModel;
import com.mvc.models.TransferenciasModel;
import com.mvc.services.TransferenciasService;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin(origins = "http://localhost:4200")
public class TransferenciasController {

	@Autowired
	TransferenciasService transferenciasService;
	
	@GetMapping()
	public ResponseEntity<ArrayList<TransferenciasModel>> obtenerTransferencias(){
		return ResponseEntity.ok(transferenciasService.obtenerTransferencias());
	}
	
	@PostMapping()
//	RequestBody se utiliza para capturar los parámetros de la URL
	public ResponseEntity<Void> guardarTransferencia(@RequestBody TransferenciasModel transferencia,  UriComponentsBuilder ucb) {
		TransferenciasModel savedTransferencia = this.transferenciasService.guardarTransferencia(transferencia);
//		UriComponentsBuilder se utiliza para crear la URI/URL de respuesta una vez creado el registro
		URI locationOfNewCashCard = ucb
				.path("transferencia/{id}")
				.buildAndExpand(savedTransferencia.getId())
				.toUri();
//		ResponseEntity devuelve una Response CREATED (201)
		return ResponseEntity.created(locationOfNewCashCard).build();
	}
	
	@GetMapping(path="/{id}")
//	PathVariable captura la variable de la uri
	public ResponseEntity<Optional<TransferenciasModel>> obtenerTransferenciaPorId(@PathVariable("id") long id){
		Optional<TransferenciasModel> transferencia = this.transferenciasService.obtenerPorId(id);
		if (transferencia.isPresent()) {
			return ResponseEntity.ok(transferencia);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.transferenciasService.eliminarTransferencia(id);
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
