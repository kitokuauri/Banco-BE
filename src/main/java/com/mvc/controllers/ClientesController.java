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
	
	@GetMapping(path="/query")
	public ArrayList<ClientesModel> obtenerClientesPorNombre(@RequestParam("nombre") String nombre){
		return this.clientesService.obtenerPorNombre(nombre);
	}
	
	@DeleteMapping(path="/{id}")
	public void eliminarPorId(@PathVariable("id") long id) {
		boolean resultado = this.clientesService.eliminarCliente(id);
		if(resultado) {
			System.out.println("Se eliminó el cliente con id " + id);
		} else {
			System.out.println("No se pudo eliminar el cliente con id " + id);
		}
	}
	
	@PatchMapping(path = "/{id}")
	public void actualizarCliente(@PathVariable("id") long id, @RequestBody Map<String, Object> cambios) {
		boolean resultado = this.clientesService.actualizarCliente(id, cambios);
		if(resultado) {
			System.out.println("Se actualizó el cliente con id " + id);
		} else {
			System.out.println("No se pudo actualizar el cliente con id " + id);
		}
	}
	

	
	
}
