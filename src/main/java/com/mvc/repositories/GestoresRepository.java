package com.mvc.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mvc.models.GestoresModel;

@Repository
public interface GestoresRepository extends CrudRepository<GestoresModel, Long>{

	public abstract ArrayList<GestoresModel> findByNombre(String nombre);

	@Query("SELECT g FROM GestoresModel g WHERE g.nombre LIKE %:nombre% OR g.apellido LIKE %:nombre%")
	ArrayList<GestoresModel> searchByNombreLike(@Param("nombre") String nombre);

	
}
