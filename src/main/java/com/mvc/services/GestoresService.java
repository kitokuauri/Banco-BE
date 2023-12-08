package com.mvc.services;

import java.util.ArrayList;
import java.util.Map;
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
		return gestoresRepository.findByNombre(nombre);
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
	
	public boolean actualizarGestor(long id, Map<String, Object> cambios) {
        try {
			Optional<GestoresModel> gestorExiste = gestoresRepository.findById(id);
	
	        if (gestorExiste.isPresent()) {
	            GestoresModel gestor = gestorExiste.get();
	
	            cambios.forEach((campo, valor) -> {
	                switch (campo) {
	                    case "nombre":
	                        gestor.setNombre((String) valor);
	                        break;
	                    case "apellido":
	                        gestor.setApellido((String) valor);
	                        break;
	                    case "edad":
	                        gestor.setEdad((int) valor);
	                        break;
	                    case "email":
	                        gestor.setEmail((String) valor);
	                        break;
	                    case "salario":
	                        gestor.setSalario((double) valor);
	                        break;
	                }
	            });
	            gestoresRepository.save(gestor);
	            return true;
	        } else {
	        	return false;
	        }
        }catch (Exception err){
        	return false;
        }
    }
	
}
