package com.G3.scm.servico;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.G3.scm.model.Veiculo;
import com.G3.scm.model.VeiculoRepository;

@Service
public class VeiculoServicoI implements VeiculoServico {
	Logger logger = LogManager.getLogger(VeiculoServicoI.class);
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	public Iterable<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}

	public Veiculo findByPlaca(String placa) {
		return veiculoRepository.findByPlaca(placa);
	}

	public void deleteById(Long id) {
		veiculoRepository.deleteById(id);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + id);
	}

	public Veiculo findById(Long id) {
		return veiculoRepository.findById(id).get();
	}

	public ModelAndView saveOrUpdate(Veiculo veiculo) {
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		try {
			veiculoRepository.save(veiculo);
			logger.info(">>>>>> 4. comando save executado  ");
			modelAndView.addObject("veiculos", veiculoRepository.findAll());
		} catch (Exception e) {
			modelAndView.setViewName("cadastrarVeiculo");
			if (e.getMessage().contains("could not execute statement")) {
				modelAndView.addObject("message", "Dados invalidos - veiculo já cadastrado.");
				logger.info(">>>>>> 5. veiculo ja cadastrado ==> " + e.getMessage());
			} else {
				modelAndView.addObject("message", "Erro não esperado - contate o administrador");
				logger.error(">>>>>> 5. erro nao esperado ==> " + e.getMessage());
			}
		}
		return modelAndView;
	}

}
