package com.mvc.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mvc.models.ClientesModel;

@Repository
public interface ClientesRepository extends CrudRepository<ClientesModel, Long> {
	
	public abstract ArrayList<ClientesModel> findByNombre(String nombre);
	
	@Query("SELECT c FROM ClientesModel c WHERE c.nombre LIKE %:nombre% OR c.apellido LIKE %:nombre%")
	ArrayList<ClientesModel> searchByNombreLike(@Param("nombre") String nombre);
	
}
