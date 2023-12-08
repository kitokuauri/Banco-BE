package com.mvc.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
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
	
	public ArrayList<MensajesModel> obtenerPorRemitente(String remitente){
		return mensajesRepository.findByRemitente(remitente);
	}
	
	public ArrayList<MensajesModel> obtenerPorDestinatario(String destinatario){
		return mensajesRepository.findByDestinatario(destinatario);
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
	
	public boolean actualizarMensaje(long id, Map<String, Object> cambios) {
        try {
			Optional<MensajesModel> mensajeExiste = mensajesRepository.findById(id);
	
	        if (mensajeExiste.isPresent()) {
	        	MensajesModel mensaje = mensajeExiste.get();
	
	            cambios.forEach((campo, valor) -> {
	                switch (campo) {
	                	case "id":
	                		mensaje.setId((long) valor);
	                		break;
	                	case "id_remitente":
	                		mensaje.setId_remitente((ClientesModel) valor);
	                		break;
	                    case "remitente":
	                    	mensaje.setRemitente((String) valor);
	                        break;
	                    case "id_destinatario":
	                    	mensaje.setId_destinatario((ClientesModel) valor);
	                		break;
	                    case "destinatario":
	                    	mensaje.setDestinatario((String) valor);
	                        break;
	                    case "mensaje":
	                    	mensaje.setMensaje((String) valor);
	                        break;
	                    case "fecha":
	                    	mensaje.setFecha((Date) valor);
	                    	break;
	                }
	            });
	            mensajesRepository.save(mensaje);
	            return true;
	        } else {
	        	return false;
	        }
	    }catch (Exception err){
	    	return false;
	    }
    }
	
}
