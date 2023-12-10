package com.mvc.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.models.ClientesModel;
import com.mvc.models.TransferenciasModel;
import com.mvc.repositories.TransferenciasRepository;

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
	
	public ArrayList<TransferenciasModel> obtenerPorIdRemitente(ClientesModel id_remitente){
		return transferenciasRepository.findByRemitente(id_remitente);
	}
	
	public ArrayList<TransferenciasModel> obtenerPorIdDestinatario(ClientesModel id_destinatario){
		return transferenciasRepository.findByDestinatario(id_destinatario);
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
	
}
