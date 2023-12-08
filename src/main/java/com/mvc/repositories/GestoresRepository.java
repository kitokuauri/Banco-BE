package com.mvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mvc.models.GestoresModel;

@Repository
public interface GestoresRepository extends CrudRepository<GestoresModel, Long>{

}
