package com.monkeys.cobranca.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull
	@NotEmpty(message = "Nome é obrigatório")
	@Size(max = 60, message = "A descrição nao pode conter mais de 60 caracteres")
	private String nome;

	@NotNull
	@NotEmpty(message = "Sobrenome é obrigatório")
	@Size(max = 60, message = "A descrição nao pode conter mais de 60 caracteres")
	private String sobrenome;

	@NotNull(message="Telefone Obrigatorio")
	private Long telefone;

	@NotEmpty(message = "Email é obrigatório")
	@Size(max = 200, message = "A descrição nao pode conter mais de 200 caracteres")
	private String email;

	@NotNull
	@NotEmpty(message = "Cpf é obrigatorio")
	@Size(max = 11, message = "O cpf nao pode conter mais de 11 caracteres")
	private String cpf;

	@Enumerated(EnumType.STRING)
	private StatusUsuario statususer;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Data de nascimento é obrigatório")
	private Date dataNascimento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public StatusUsuario getStatususer() {
		return statususer;
	}

	public void setStatususer(StatusUsuario statususer) {
		this.statususer = statususer;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isInativo() {
		return StatusUsuario.INATIVO.equals(this.statususer);
	}

	
	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}