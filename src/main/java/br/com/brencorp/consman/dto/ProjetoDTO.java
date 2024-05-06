package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Projeto;

public class ProjetoDTO {

	private Long id;
	private String nome;
	
	public ProjetoDTO() {
	}

	public ProjetoDTO(Long id, String descricao) {
		this.id = id;
		this.nome = descricao;
	}
	
	public ProjetoDTO(Projeto projeto) {
		id = projeto.getId();
		nome = projeto.getNome();
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
}
