package com.mvc.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.models.GestoresModel;

@Repository
public interface GestoresRepository extends CrudRepository<GestoresModel, Long>{

	public abstract ArrayList<GestoresModel> findByNombre(String nombre);

	
}
