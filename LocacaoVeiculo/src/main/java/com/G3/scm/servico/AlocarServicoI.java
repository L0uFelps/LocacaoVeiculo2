package com.G3.scm.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.G3.scm.model.Alocar;
import com.G3.scm.model.AlocarRepository;
import com.G3.scm.model.Cliente;

import com.G3.scm.model.Veiculo;


@Service
public class AlocarServicoI implements AlocarServico {
	Logger logger = LogManager.getLogger(AlocarServicoI.class);
	@Autowired
	private AlocarRepository alocarRepository;
	@Autowired
	private ClienteServico servicoC;
	@Autowired
	private VeiculoServico servicoV;
	
	public Iterable<Alocar> findAll() {
		return alocarRepository.findAll();
	}
	
	
	
	public Alocar findById(Long id) {
		return alocarRepository.findById(id).get();
	}
	
	

	public ModelAndView saveOrUpdate(Alocar alocacao) {
		ModelAndView modelAndView = new ModelAndView("consultarAlocacoes");
		Cliente cliente = servicoC.findByCpf(alocacao.getClienteCpf());
		Veiculo veiculo = servicoV.findByPlaca(alocacao.getVeiculoPlaca());
		try {
			alocacao.setDtInicio(alocacao.getDtInicioFormat());
			alocacao.setDtEntrega(alocacao.getDtEntregaFormat());
			int verifDataEntrega =  alocacao.getDtEntrega().getDayOfYear(); 
			int verifDataInicio = alocacao.getDtInicio().getDayOfYear();
			if(cliente.isAlocacao() == false && veiculo.isLocado() == false && verifDataEntrega >= verifDataInicio) {
				alocacao.setClienteId(cliente.getId());
				alocacao.setClienteNome(cliente.getNome());
				alocacao.setVeiculoId(veiculo.getId());
				alocacao.setVeiculoNome(veiculo.getNome());
				float valorT = (verifDataEntrega - verifDataInicio) * veiculo.getValorDiaria();
				alocacao.setValorTotal(valorT);
				alocacao.setSituacao(true);
				cliente.setAlocacao(true);
				veiculo.setLocado(true);
				alocarRepository.save(alocacao);
				logger.info(">>>>>> 4. comando save executado  ");
				modelAndView.addObject("alocados", alocarRepository.findAll());
			}
			else {
				modelAndView.setViewName("alocar");
				if(verifDataInicio > verifDataEntrega) {
					modelAndView.addObject("message", "Data de Entrega invalida");
				}
				else {
					modelAndView.addObject("message", "Cliente ou veiculo já possui alguma alocação");
				}
				
			}
			
		} catch (Exception e) {
			modelAndView.setViewName("alocar");
			if (e.getMessage().contains("could not execute statement")) {
				modelAndView.addObject("message", "Dados invalidos - cliente já cadastrado.");
				logger.info(">>>>>> 5. cliente ja cadastrado ==> " + e.getMessage());
			} else {
				if(cliente == null || veiculo == null) {
					modelAndView.addObject("message", "Dados invalidos - cliente ou veiculo não encontrado.");
				}
				else {
					modelAndView.addObject("message", "Erro não esperado - contate o administrador");
					logger.error(">>>>>> 5. erro nao esperado ==> " + e.getMessage());
				}
				
			}
		}
		return modelAndView;
	}
	
	public ModelAndView saveOrUpdate2(Alocar alocacao) {
		ModelAndView modelAndView = new ModelAndView("consultarAlocacoes");
		Cliente cliente = servicoC.findByCpf(alocacao.getClienteCpf());
		Veiculo veiculo = servicoV.findByPlaca(alocacao.getVeiculoPlaca());
		try {
			alocacao.setClienteId(cliente.getId());
			alocacao.setClienteNome(cliente.getNome());
			alocacao.setVeiculoId(veiculo.getId());
			alocacao.setVeiculoNome(veiculo.getNome());
			alocacao.setDtInicio(alocacao.getDtInicioFormat());
			alocacao.setDtEntrega(alocacao.getDtEntregaFormat());
			float valorT = (alocacao.getDtEntrega().getDayOfMonth() - alocacao.getDtInicio().getDayOfMonth()) * veiculo.getValorDiaria();
			alocacao.setValorTotal(valorT);
			alocarRepository.save(alocacao);
			logger.info(">>>>>> 4. comando save executado  ");
			modelAndView.addObject("alocados", alocarRepository.findAll());
			
		} catch (Exception e) {
			modelAndView.setViewName("alocar");
			if (e.getMessage().contains("could not execute statement")) {
				modelAndView.addObject("message", "Dados invalidos - cliente já cadastrado.");
				logger.info(">>>>>> 5. cliente ja cadastrado ==> " + e.getMessage());
			} else {
				modelAndView.addObject("message", "Erro não esperado - contate o administrador");
				logger.error(">>>>>> 5. erro nao esperado ==> " + e.getMessage());
			}
		}
		return modelAndView;
	}
}
