package com.mvc.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.models.ClientesModel;
import com.mvc.models.TransferenciasModel;

@Repository
public interface TransferenciasRepository extends CrudRepository<TransferenciasModel, Long>{

	public abstract ArrayList<TransferenciasModel> findByRemitente(ClientesModel id_remitente);
	
	public abstract ArrayList<TransferenciasModel> findByDestinatario(ClientesModel id_destinatario);
	
}
