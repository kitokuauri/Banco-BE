package com.mvc.services;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.models.ClientesModel;
import com.mvc.models.TransferenciasModel;
import com.mvc.repositories.TransferenciasRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransferenciasService {

	@Autowired
	TransferenciasRepository transferenciasRepository;
	
//	Sirve tanto para insertar como actualizar (si ponemos un id existente)
	public TransferenciasModel guardarTransferencia(TransferenciasModel transferencia) {
		return transferenciasRepository.save(transferencia);
	}
	
//	cuando tratamos con id => clase Optional
	public Optional<TransferenciasModel> obtenerPorId(long id) {
		return transferenciasRepository.findById(id);
	}
	
	public ArrayList<TransferenciasModel> obtenerTransferencias(){
		return (ArrayList<TransferenciasModel>)transferenciasRepository.findAll();
	}
	
	public boolean eliminarTransferencia(long id) {
		try {
			if(transferenciasRepository.existsById(id)) {
				transferenciasRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch(Exception err) {
			return false;
		}
	}
	
	public TransferenciasModel actualizarTransferencia(long id, Map<String, Object> cambios) {
        Optional<TransferenciasModel> transferenciaExiste = transferenciasRepository.findById(id);

        if (transferenciaExiste.isPresent()) {
        	TransferenciasModel transferencia = transferenciaExiste.get();

            cambios.forEach((campo, valor) -> {
                switch (campo) {
                case "id":
                	transferencia.setId((long) valor);
            		break;
            	case "id_remitente":
            		transferencia.setId_remitente((ClientesModel) valor);
            		break;
                case "remitente":
                	transferencia.setRemitente((String) valor);
                    break;
                case "id_destinatario":
                	transferencia.setId_destinatario((ClientesModel) valor);
            		break;
                case "destinatario":
                	transferencia.setDestinatario((String) valor);
                    break;
                case "cantidad":
                	transferencia.setCantidad((double) valor);
                    break;
                }
            });

            return transferenciasRepository.save(transferencia);
        } else {
            throw new EntityNotFoundException("Transferencia no encontrada con ID: " + id);
        }
    }
	
}
