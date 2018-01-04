package com.monkeys.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.monkeys.cobranca.model.StatusUsuario;
import com.monkeys.cobranca.model.Usuario;
import com.monkeys.cobranca.repository.Usuarios;
import com.monkeys.cobranca.repository.filter.UsuarioFilter;

//@Service para fazer ficar visivel na Usuario Controller/
@Service
public class CadastroUsuarioService {

	// injetando o repositório
	@Autowired
	private Usuarios usuarios;

	public void salvar(Usuario usuario) {
		try {

			usuarios.save(usuario);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}

	}

	public void excluir(long codigo) {
		usuarios.delete(codigo);

	}

	public String receber(long codigo) {
		Usuario usuario = usuarios.findOne(codigo);
		usuario.setStatususer(StatusUsuario.ATIVO);
		usuarios.save(usuario);

		return StatusUsuario.ATIVO.getDesricao();

	}

	public List<Usuario> filtrar(UsuarioFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return usuarios.findByNomeContaining(nome);
	}
	
	

}
