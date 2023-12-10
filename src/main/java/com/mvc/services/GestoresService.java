package com.mvc.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.models.GestoresModel;
import com.mvc.repositories.GestoresRepository;


@Service
public class GestoresService {

	@Autowired
	GestoresRepository gestoresRepository;
	
//	Sirve tanto para insertar como actualizar (si ponemos un id existente)
	public GestoresModel guardarGestor(GestoresModel gestor) {
		return gestoresRepository.save(gestor);
	}
	
//	cuando tratamos con id => clase Optional
	public Optional<GestoresModel> obtenerPorId(long id) {
		return gestoresRepository.findById(id);
	}
	
	public ArrayList<GestoresModel> obtenerPorNombre(String nombre){
		return gestoresRepository.searchByNombreLike(nombre);
	}
	
	public ArrayList<GestoresModel> obtenerGestores(){
		return (ArrayList<GestoresModel>)gestoresRepository.findAll();
	}
	
	public boolean eliminarGestor(long id) {
		try {
			if(gestoresRepository.existsById(id)) {
				gestoresRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch(Exception err) {
			return false;
		}
	}
	
	public boolean actualizarGestor(long id, GestoresModel cambios) {
        try {
			Optional<GestoresModel> gestorExiste = gestoresRepository.findById(id);
	
			if (gestorExiste.isPresent()) {
				GestoresModel gestor = gestorExiste.get();
				GestoresModel updatedGestor = new GestoresModel(
						gestor.getId(),
						cambios.getNombre(),
						cambios.getApellido(),
						cambios.getEmail(),
						cambios.getEdad(),
						cambios.getSalario()
						);
				gestoresRepository.save(updatedGestor);
				
				return true;
			}
			
			return false;
        }catch (Exception err){
        	return false;
        }
    }
	
}
