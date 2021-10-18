package com.G3.scm.model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocarRepository extends CrudRepository<Alocar, Long> {
	public Alocar findById(@Param("id") String id);

	
}
