package com.mvc.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mvc.services.ClientesService;
import com.mvc.models.ClientesModel;

@RestController
// Definición de la ruta de Cliente
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientesController {
	
	@Autowired
	ClientesService clientesService;
	
	@GetMapping()
	public ArrayList<ClientesModel> obtenerClientes(){
		return clientesService.obtenerClientes();
	}
	
	@PostMapping()
//	RequestBody se utiliza para capturar los parámetros de la URL
	public ClientesModel guardarCliente(@RequestBody ClientesModel cliente) {
		return this.clientesService.guardarCliente(cliente);
	}
	
	@GetMapping(path="/{id}")
//	PathVariable captura la variable de la uri
	public Optional<ClientesModel> obtenerClientePorId(@PathVariable("id") long id){
		return this.clientesService.obtenerPorId(id);
	}
	
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.clientesService.eliminarCliente(id);
		if(resultado) {
			return "Se eliminó el usuario con id " + id;
		} else {
			return "No se pudo eliminar el usuario con id " + id;
		}
	}
	
	@PatchMapping(path = "/{id}")
	public ClientesModel actualizarCliente(@PathVariable("id") long id, @RequestBody Map<String, Object> cambios) {
		return this.clientesService.actualizarCliente(id, cambios);
	}
	

	
	
}
