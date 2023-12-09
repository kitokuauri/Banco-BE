package com.mvc.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.repositories.ClientesRepository;

import com.mvc.models.ClientesModel;

@Service
public class ClientesService {
	
//	Instancia que realiza la Inyección de la dependencia (pomp)
	@Autowired
	ClientesRepository clientesRepository;
	
//	Sirve tanto para insertar como actualizar (si ponemos un id existente)
	public ClientesModel guardarCliente(ClientesModel cliente) {
		return clientesRepository.save(cliente);
	}
	
//	cuando tratamos con id => clase Optional
	public Optional<ClientesModel> obtenerPorId(long id) {
		return clientesRepository.findById(id);
	}
	
	public ArrayList<ClientesModel> obtenerPorNombre(String nombre){
		return clientesRepository.findByNombre(nombre);
	}
	
	public ArrayList<ClientesModel> obtenerClientes(){
		return (ArrayList<ClientesModel>)clientesRepository.findAll();
	}

	public boolean eliminarCliente(long id) {
		try {
			if(clientesRepository.existsById(id)) {
				clientesRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch(Exception err) {
			return false;
		}
	}
	
	public boolean actualizarCliente(long id, ClientesModel cambios) {
		try {
			Optional<ClientesModel> clienteExiste = clientesRepository.findById(id);
	
			if (clienteExiste.isPresent()) {
				ClientesModel cliente = clienteExiste.get();
				ClientesModel updatedCliente = new ClientesModel(
						cliente.getId(),
						cambios.getNombre(),
						cambios.getApellido(),
						cambios.getEmail(),
						cambios.getGestor()
						);
				clientesRepository.save(updatedCliente);
				
				return true;
			}
			
			return false;
	    }catch (Exception err){
	    	return false;
	    }
	}
	
}
