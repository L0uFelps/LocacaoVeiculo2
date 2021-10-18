package com.G3.scm.servico;

import org.springframework.web.servlet.ModelAndView;
import com.G3.scm.model.Cliente;
import com.G3.scm.model.Endereco;

public interface ClienteServico {
	public Iterable<Cliente> findAll();

	public Cliente findByCpf(String cpf);

	public void deleteById(Long id);

	public Cliente findById(Long id);

	public ModelAndView saveOrUpdate(Cliente cliente);

	public Endereco obtemEndereco(String cep);
}
