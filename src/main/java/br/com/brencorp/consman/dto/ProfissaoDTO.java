package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Profissao;

public class ProfissaoDTO {
	
	private Long id;
	private String nome;
	private String area;
	
	public ProfissaoDTO() {
	}

	public ProfissaoDTO(Long id, String nome, String area) {
		this.id = id;
		this.nome = nome;
		this.area = area;
	}
	
	public ProfissaoDTO(Profissao profissao) {
		id = profissao.getId();
		nome = profissao.getNome();
		area = profissao.getArea();
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
