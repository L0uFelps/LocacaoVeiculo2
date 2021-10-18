package com.G3.scm.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Alocar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long clienteId;
	@CPF(message = "CPF invalido")
	private String clienteCpf;
	private String clienteNome;
	private Long veiculoId;
	@NotNull
	@Size(min = 7, max = 7, message = "Placa invalida")
	private String veiculoPlaca;
	private String veiculoNome;
	private boolean situacao;
	private String dtInicioFormat;
	@NotNull
	@Size(min = 10, max = 10, message = "Data invalida")
	private String dtEntregaFormat;
	private LocalDate dtInicio;
	private LocalDate dtEntrega;
	private float valorTotal;
	
	public Alocar() {}
	public Alocar(String cpf, String placa, String dtInicioFormat, String dtEntregaFormat ) {
		this.clienteCpf = cpf;
		this.veiculoPlaca = placa;
		this.dtInicioFormat = dtInicioFormat;
		this.dtEntregaFormat = dtEntregaFormat;
		this.situacao = false;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public String getClienteCpf() {
		return clienteCpf;
	}
	public void setClienteCpf(String clienteCpf) {
		this.clienteCpf = clienteCpf;
	}
	public String getClienteNome() {
		return clienteNome;
	}
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	public Long getVeiculoId() {
		return veiculoId;
	}
	public void setVeiculoId(Long veiculoId) {
		this.veiculoId = veiculoId;
	}
	public String getVeiculoPlaca() {
		return veiculoPlaca;
	}
	public void setVeiculoPlaca(String veiculoPlaca) {
		this.veiculoPlaca = veiculoPlaca;
	}
	public String getVeiculoNome() {
		return veiculoNome;
	}
	public void setVeiculoNome(String veiculoNome) {
		this.veiculoNome = veiculoNome;
	}
	public boolean isSituacao() {
		return situacao;
	}
	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	public String getDtInicioFormat() {
		return dtInicioFormat;
	}
	public void setDtInicioFormat(String dtInicioFormat) {
		this.dtInicioFormat = dtInicioFormat;
	}
	public String getDtEntregaFormat() {
		return dtEntregaFormat;
	}
	public void setDtEntregaFormat(String dtEntregaFormat) {
		this.dtEntregaFormat = dtEntregaFormat;
	}
	public LocalDate getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(String dtInicio) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dtInicio, formato); 
		this.dtInicio = data;
		
	}
	public LocalDate getDtEntrega() {
		return dtEntrega;
	}
	public void setDtEntrega(String dtEntrega) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dtEntrega, formato); 
		this.dtEntrega = data;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	
	
}
