package com.mvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.models.ClientesModel;

@Repository
public interface ClientesRepository extends CrudRepository<ClientesModel, Long> {

}
