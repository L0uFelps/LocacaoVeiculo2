package com.G3.scm.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HomeController {
	@GetMapping("/")
	public ModelAndView menu() {
		return new ModelAndView("index");
	}
	
	/*@GetMapping("/cliente")
	public ModelAndView menuCliente() {
		return new ModelAndView("menuCliente");
	}
	
	@GetMapping("/cadastrarCliente")
	public ModelAndView cadastrarCliente() {
		return new ModelAndView("cadastrarCliente");
	}
	@GetMapping("/consultarCliente")
	public ModelAndView consultarCliente() {
		return new ModelAndView("consultarCliente");
	}
	
	@GetMapping("/veiculo")
	public ModelAndView menuVeiculo() {
		return new ModelAndView("menuVeiculo");
	}
	
	@GetMapping("/cadastrarVeiculo")
	public ModelAndView cadastrarVeiculo() {
		return new ModelAndView("cadastrarVeiculo");
	}
	@GetMapping("/consultarVeiculo")
	public ModelAndView consultarVeiculo() {
		return new ModelAndView("consultarVeiculo");
	}*/
	
	/*@GetMapping("/alocar")
	public ModelAndView alocar() {
		return new ModelAndView("alocar");
	}*/
	
	/*@GetMapping("/desalocar")
	public ModelAndView desalocar() {
		return new ModelAndView("desalocar");
	}*/
	@GetMapping("/login")
	public ModelAndView paginaLogin() {
		return new ModelAndView("paginaLogin");
	}
	
	
	
}
