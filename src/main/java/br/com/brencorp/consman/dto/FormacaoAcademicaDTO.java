package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.FormacaoAcademica;

public class FormacaoAcademicaDTO {

	private Long id;
	private String nome;
	private String instituicao;
	private String tipo;
	private Integer anoConclusao;
	private Integer tempoFormacao;

	public FormacaoAcademicaDTO() {
	}

	public FormacaoAcademicaDTO(Long id, String nome, String instituicao, String tipo, Integer anoConclusao) {
		this.id = id;
		this.nome = nome;
		this.instituicao = instituicao;
		this.tipo = tipo;
		this.anoConclusao = anoConclusao;
	}

	public FormacaoAcademicaDTO(FormacaoAcademica formacaoAcademica) {
		id = formacaoAcademica.getId();
		nome = formacaoAcademica.getNome();
		instituicao = formacaoAcademica.getInstituicao();
		tipo = formacaoAcademica.getTipo();
		anoConclusao = formacaoAcademica.getAnoConclusao();
		tempoFormacao = formacaoAcademica.getTempoFormacao();
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

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getAnoConclusao() {
		return anoConclusao;
	}

	public void setAnoConclusao(Integer anoConclusao) {
		this.anoConclusao = anoConclusao;
	}

	public Integer getTempoFormacao() {
		return tempoFormacao;
	}
}
