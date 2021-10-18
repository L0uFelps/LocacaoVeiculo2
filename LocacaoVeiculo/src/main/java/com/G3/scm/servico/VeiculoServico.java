package com.G3.scm.servico;

import org.springframework.web.servlet.ModelAndView;
import com.G3.scm.model.Veiculo;

public interface VeiculoServico {
	public Iterable<Veiculo> findAll();

	public Veiculo findByPlaca(String placa);

	public void deleteById(Long id);

	public Veiculo findById(Long id);

	public ModelAndView saveOrUpdate(Veiculo veiculo);

	
}
