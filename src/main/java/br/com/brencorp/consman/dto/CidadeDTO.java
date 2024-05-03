package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Cidade;
import br.com.brencorp.consman.entities.Estado;

public class CidadeDTO {
	
	private Long id;
	private String nome;
	private Estado estado;
	
	public CidadeDTO() {
	}

	public CidadeDTO(Long id, String nome, Estado estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}
	
	public CidadeDTO(Cidade cidade) {
		id = cidade.getId();
		nome = cidade.getNome();
		estado = cidade.getEstado();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
