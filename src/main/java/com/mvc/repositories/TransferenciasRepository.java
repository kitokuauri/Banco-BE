package com.mvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.models.TransferenciasModel;

@Repository
public interface TransferenciasRepository extends CrudRepository<TransferenciasModel, Long>{

}
