package com.mvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.models.MensajesModel;

@Repository
public interface MensajesRepository extends CrudRepository<MensajesModel, Long>{

}
