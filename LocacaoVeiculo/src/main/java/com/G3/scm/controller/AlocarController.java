package com.G3.scm.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.G3.scm.model.Alocar;
import com.G3.scm.model.Cliente;
import com.G3.scm.model.Veiculo;
import com.G3.scm.servico.AlocarServico;
import com.G3.scm.servico.ClienteServico;
import com.G3.scm.servico.VeiculoServico;

@Controller
@RequestMapping(path = "/sig")
public class AlocarController {
	Logger logger = LogManager.getLogger(AlocarController.class);
	@Autowired
	AlocarServico servico;
	@Autowired
	ClienteServico servicoC;
	@Autowired
	VeiculoServico servicoV;
	
	@GetMapping("/alocados")
	public ModelAndView retornaFormDeConsultaTodosAlocacoes() {
		ModelAndView modelAndView = new ModelAndView("consultarAlocacoes");
		modelAndView.addObject("alocados", servico.findAll());
		return modelAndView;
	}

	@GetMapping("/alocar")
	public ModelAndView retornaFormDeCadastroDe(Alocar alocar) {
		ModelAndView mv = new ModelAndView("alocar");
		mv.addObject("alocar", alocar);
		return mv;
	}

	@GetMapping("/alocados/{id}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarAlocacao(@PathVariable("id") Long id) {
		Alocar alocacao = servico.findById(id);
		ModelAndView modelAndView = new ModelAndView("desalocar");
		if(alocacao.isSituacao() == true) {
			modelAndView.addObject("alocar", servico.findById(id)); // o repositorio e injetado no controller
		}
		else {
			modelAndView.setViewName("consultarAlocacoes");
			modelAndView.addObject("alocados", servico.findAll());
			modelAndView.addObject("message", "Alocação já foi encerrada");
		}
		
		return modelAndView; // addObject adiciona objetos para view
	}

	

	@PostMapping("/alocados")
	public ModelAndView save(Alocar alocar, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarAlocacoes");
		if (result.hasErrors()) {
			modelAndView.setViewName("alocar");
		} else {
			modelAndView = servico.saveOrUpdate(alocar);
		}
		return modelAndView;
	}

	@PostMapping("/alocados/{id}")
	public ModelAndView atualizaAlocacao(@PathVariable("id") Long id, Alocar alocar, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarAlocacoes");
		if (result.hasErrors()) {
			alocar.setId(id);
			return new ModelAndView("desalocar");
		}
// programacao defensiva - deve-se verificar se o Cliente existe antes de atualizar
		Alocar umaAlocagem = servico.findById(id);
		Cliente umCliente = servicoC.findByCpf(umaAlocagem.getClienteCpf());
		Veiculo umVeiculo = servicoV.findByPlaca(umaAlocagem.getVeiculoPlaca());
		umaAlocagem.setSituacao(false);
		umCliente.setAlocacao(false);
		umVeiculo.setLocado(false);
		modelAndView = servico.saveOrUpdate2(umaAlocagem);
		return modelAndView;
	}
	
	/*@GetMapping("/desalocar")
	public ModelAndView desalocacao(Alocar alocar) {
		ModelAndView mv = new ModelAndView("desalocar");
		mv.addObject("alocar", alocar);
		return mv;
	}*/
	
	/*@PostMapping("/realizarDesalocacao")
	public ModelAndView DesalocarCliente( Alocar alocar) {
		ModelAndView modelAndView = new ModelAndView("consultarAlocacoes");
		Cliente umCliente = servicoC.findByCpf(alocar.getClienteCpf());
		Veiculo umVeiculo = servicoV.findByPlaca(alocar.getVeiculoPlaca());
		Alocar umaAlocagem = servico.findById(umCliente.getAlocacaoId());
		if(umaAlocagem.isSituacao() == false ) {
			return new ModelAndView("desalocar");
		}
		
		else {
			umaAlocagem.setSituacao(false);
			umCliente.setAlocacao(false);
			umVeiculo.setLocado(false);
			modelAndView = servico.saveOrUpdate(umaAlocagem);
			return modelAndView;
		}
		
	}*/
	
}
