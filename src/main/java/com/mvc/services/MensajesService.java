package com.mvc.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.models.ClientesModel;
import com.mvc.models.MensajesModel;
import com.mvc.repositories.MensajesRepository;


@Service
public class MensajesService {

	@Autowired
	MensajesRepository mensajesRepository;
	
//	Sirve tanto para insertar como actualizar (si ponemos un id existente)
	public MensajesModel guardarMensaje(MensajesModel mensaje) {
		return mensajesRepository.save(mensaje);
	}
	
//	cuando tratamos con id => clase Optional
	public Optional<MensajesModel> obtenerPorId(long id) {
		return mensajesRepository.findById(id);
	}
	
	public ArrayList<MensajesModel> obtenerPorIdRemitente(ClientesModel id_remitente){
		return mensajesRepository.findByRemitente(id_remitente);
	}
	
	public ArrayList<MensajesModel> obtenerPorIdDestinatario(ClientesModel id_destinatario){
		return mensajesRepository.findByDestinatario(id_destinatario);
	}
	
	public ArrayList<MensajesModel> obtenerMensajes(){
		return (ArrayList<MensajesModel>)mensajesRepository.findAll();
	}
	
	public boolean eliminarMensaje(long id) {
		try {
			if(mensajesRepository.existsById(id)) {
				mensajesRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch(Exception err) {
			return false;
		}
	}
	

	
}
