package com.monkeys.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.monkeys.cobranca.model.StatusUsuario;
import com.monkeys.cobranca.model.Usuario;
import com.monkeys.cobranca.repository.filter.UsuarioFilter;
import com.monkeys.cobranca.service.CadastroUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	private static final String USUARIO_VIEW = "CadastroUsuario";


	// Chamando camada de Servicos//
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mvu = new ModelAndView(USUARIO_VIEW);
		mvu.addObject(new Usuario());

		return mvu;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Usuario usuario, Errors errors, RedirectAttributes atrributes) {
		if (errors.hasErrors()) {
			return USUARIO_VIEW;
		}

		try {

			cadastroUsuarioService.salvar(usuario);
			atrributes.addFlashAttribute("mensagem", "Usuario Salvo com Sucesso");
			return "redirect:/usuarios/novo";
		} catch (IllegalArgumentException e) {

			errors.rejectValue("dataNascimento", null, e.getMessage());
			return USUARIO_VIEW;
		}
	}

	// Chamado Pagina PesquisaUsuarios
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") UsuarioFilter filtro) {
		
		List<Usuario> todosUsuarios = cadastroUsuarioService.filtrar(filtro);
		ModelAndView mvu = new ModelAndView("PesquisaUsuarios");
		mvu.addObject("usuarios",todosUsuarios);
		return mvu;
	

	}

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Usuario usuario) {
		ModelAndView mvu = new ModelAndView(USUARIO_VIEW);
		mvu.addObject(usuario);
		return mvu;

	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String exclusao(@PathVariable long codigo, RedirectAttributes atrributes) {
		cadastroUsuarioService.excluir(codigo);

		atrributes.addFlashAttribute("mensagem", "Usuário Excluido com Sucesso");

		return "redirect:/usuarios";

	}


	
	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {
		return cadastroUsuarioService.receber(codigo);
	}
	
	
	
	@ModelAttribute("todosStatusUsuario")
	public List<StatusUsuario> todosStatusUsuario() {
		return Arrays.asList(StatusUsuario.values());
	}

	
	
}
