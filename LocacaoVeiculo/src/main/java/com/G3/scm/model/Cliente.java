package com.G3.scm.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.G3.scm.model.Endereco;


@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@CPF(message = "CPF invalido")
	@Column(unique = true)
	private String cpf;
	@NotNull
	@Size(min = 1, max = 50, message = "Nome deve ser preenchido")
	private String nome;
	@Email(message = "E-mail invalido")
	private String email;
	@NotNull
	private String cep;
	@ManyToOne
	private Endereco endereco;
	
	private boolean alocacao;
	@NotNull
	@Size(min = 10, max = 11, message = "Telefone invalido")
	private String telefone;
	private String num;
	private LocalDate dtNascimento;
	@NotNull
	@Size(min = 10, max = 10, message = "Data de nascimento invalida")
	private String dtNascimentoFormat;
	private String dataCadastro;

	public Cliente() {
	}
	public Cliente(String cpf, String nome, String email, Endereco endereco, String telefone, String num,String dataN) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.num = num;
		this.endereco = endereco;
		this.telefone = telefone;
		this.dtNascimentoFormat = dataN;
		setDtNascimento(this.dtNascimentoFormat);
		this.alocacao = false;
		DateTime dataAtual = new DateTime();
		setDataCadastro(dataAtual);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(DateTime dataAtual) {
		org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		this.dataCadastro = dataAtual.toString(fmt);
		

	}
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(String dtNascimento) {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dtNascimento, formato); 
		this.dtNascimento = data;
	}
	
	
	public String getDtNascimentoFormat() {
		return dtNascimentoFormat;
	}
	public void setDtNascimentoFormat(String dtNascimentoFormat) {
		this.dtNascimentoFormat = dtNascimentoFormat;
	}
	
	public boolean isAlocacao() {
		return alocacao;
	}
	public void setAlocacao(boolean alocacao) {
		this.alocacao = alocacao;
	}
	
	

	

	
	
	
	
	
	

}
