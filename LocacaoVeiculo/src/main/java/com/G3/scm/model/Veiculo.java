package com.G3.scm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	@Size(min = 7, max = 7)
	private String placa;
	@NotNull
	@Size(min = 1, max = 50, message = "Nome deve ser preenchido")
	private String nome;
	@NotNull
	@Size(min = 1, max = 50, message = "Modelo deve ser preenchido")
	private String modelo;
	private boolean locado;
	@NotNull
	@Size(min = 1, max = 50, message = "Cor deve ser preenchido")
	private String cor;
	@NotNull
	@Size(min = 4, max = 4, message = "Ano deve ser preenchido")
	private String ano;
	@NotNull
	private float valorDiaria;
	
	public Veiculo() {}
	
	public Veiculo(String placa, String nome,String modelo,String cor,String ano, float valorDiaria) {
		super();
		this.placa = placa;
		this.nome = nome;
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
		this.valorDiaria =valorDiaria;
		this.locado = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public boolean isLocado() {
		return locado;
	}

	public void setLocado(boolean locado) {
		this.locado = locado;
	}
	

	public float getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(float valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	

	
	
	
}
