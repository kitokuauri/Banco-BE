package com.mvc.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mvc.models.TransferenciasModel;
import com.mvc.services.TransferenciasService;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin(origins = "http://localhost:4200")
public class TransferenciasController {

	@Autowired
	TransferenciasService transferenciasService;
	
	@GetMapping()
	public ArrayList<TransferenciasModel> obtenerTransferencias(){
		return transferenciasService.obtenerTransferencias();
	}
	
	@PostMapping()
//	RequestBody se utiliza para capturar los parámetros de la URL
	public TransferenciasModel guardarTransferencia(@RequestBody TransferenciasModel transferencia) {
		return this.transferenciasService.guardarTransferencia(transferencia);
	}
	
	@GetMapping(path="/{id}")
//	PathVariable captura la variable de la uri
	public Optional<TransferenciasModel> obtenerTransferenciaPorId(@PathVariable("id") long id){
		return this.transferenciasService.obtenerPorId(id);
	}
	
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.transferenciasService.eliminarTransferencia(id);
		if(resultado) {
			return "Se eliminó el usuario con id " + id;
		} else {
			return "No se pudo eliminar el usuario con id " + id;
		}
	}
	
	@PatchMapping(path = "/{id}")
	public TransferenciasModel actualizarTransferencia(@PathVariable("id") long id, @RequestBody Map<String, Object> cambios) {
		return this.transferenciasService.actualizarTransferencia(id, cambios);
	}
	
	
}
