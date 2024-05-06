package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Profissao;
import jakarta.validation.constraints.NotBlank;

public class ProfissaoDTO {
	
	private Long id;
	@NotBlank(message = "Nome da profissão é obrigatório.")
	private String nome;
	@NotBlank(message = "Área de atuação é obrigatório.")
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
