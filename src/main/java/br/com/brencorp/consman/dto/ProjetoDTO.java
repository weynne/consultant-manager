package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Projeto;

public class ProjetoDTO {

	private Long id;
	private String descricao;
	
	public ProjetoDTO() {
	}

	public ProjetoDTO(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public ProjetoDTO(Projeto projeto) {
		id = projeto.getId();
		descricao = projeto.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
