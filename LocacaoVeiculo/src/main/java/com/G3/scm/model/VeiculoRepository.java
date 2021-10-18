package com.G3.scm.model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends CrudRepository <Veiculo,Long> {
	public Veiculo findByPlaca(@Param("placa") String placa);
}
