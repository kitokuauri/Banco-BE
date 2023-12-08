package com.mvc.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.mvc.models.MensajesModel;

@Repository
public interface MensajesRepository extends CrudRepository<MensajesModel, Long>{

	public abstract ArrayList<MensajesModel> findByRemitente(String remitente);
	
	public abstract ArrayList<MensajesModel> findByDestinatario(String destinatario);
	
}
