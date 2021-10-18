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


import com.G3.scm.model.Veiculo;
import com.G3.scm.servico.VeiculoServico;

@Controller
@RequestMapping(path = "/sig")
public class VeiculoController {
	Logger logger = LogManager.getLogger(VeiculoController.class);
	
	@Autowired
	VeiculoServico servico;

	@GetMapping("/menuVeiculo")
	public ModelAndView menuVeiculo() {
		return new ModelAndView("menuVeiculo");
	}
	
	@GetMapping("/veiculos")
	public ModelAndView retornaFormDeConsultaTodosVeiculos() {
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		modelAndView.addObject("veiculos", servico.findAll());
		return modelAndView;
	}
	
	@GetMapping("/veiculo")
	public ModelAndView retornaFormDeCadastroDeV(Veiculo veiculo) {
		ModelAndView mv = new ModelAndView("cadastrarVeiculo");
		mv.addObject("veiculo", veiculo);
		return mv;
	}
	
	@GetMapping("/veiculos/{placa}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarVeiculo(@PathVariable("placa") String placa) {
		ModelAndView modelAndView = new ModelAndView("atualizarVeiculo");
		modelAndView.addObject("veiculo", servico.findByPlaca(placa)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}
	
	@GetMapping("/veiculo/{id}")
	public ModelAndView excluirNoFormDeConsultaVeiculo(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		Veiculo veiculo = servico.findById(id);
		if(veiculo.isLocado() == true) {
			modelAndView.addObject("message", "Veiculo esta alocado nesse momento, impossivel deletar.");
		}
		else {
			servico.deleteById(id);
			logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		}
		
		
		modelAndView.addObject("veiculos", servico.findAll());
		return modelAndView;
	}
	
	@PostMapping("/veiculos")
	public ModelAndView save(@Valid Veiculo veiculo, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarVeiculo");
		} else {
			modelAndView = servico.saveOrUpdate(veiculo);
		}
		return modelAndView;
	}
	
	@PostMapping("/veiculos/{id}")
	public ModelAndView atualizaVeiculo(@PathVariable("id") Long id, @Valid Veiculo veiculo, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		if (result.hasErrors()) {
			veiculo.setId(id);
			return new ModelAndView("atualizarVeiculo");
		}
// programacao defensiva - deve-se verificar se o Cliente existe antes de atualizar
		Veiculo umVeiculo = servico.findById(id);
		umVeiculo.setPlaca(veiculo.getPlaca());
		umVeiculo.setNome(veiculo.getNome());
		umVeiculo.setModelo(veiculo.getModelo());
		umVeiculo.setCor(veiculo.getCor());
		umVeiculo.setValorDiaria(veiculo.getValorDiaria());
		umVeiculo.setAno(veiculo.getAno());
		modelAndView = servico.saveOrUpdate(umVeiculo);
		return modelAndView;
	}
}
