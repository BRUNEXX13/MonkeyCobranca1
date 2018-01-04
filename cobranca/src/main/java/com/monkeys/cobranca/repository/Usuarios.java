package com.monkeys.cobranca.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monkeys.cobranca.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {
	
	//  um like para pesquisa
	public List<Usuario> findByNomeContaining(String nome);


}

