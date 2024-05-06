package br.com.brencorp.consman.dto;

import java.util.List;

import br.com.brencorp.consman.entities.Cat;
import br.com.brencorp.consman.entities.Cidade;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.entities.Profissao;
import br.com.brencorp.consman.entities.Projeto;

public class ConsultorDTO {

	private Long id;
	private String cpf;
	private String cnpj;
	private String nome;
	private String telefone;
	private String email;
	private String dataNascimentoString;
	private Integer idade;
	private Cidade cidade;

	private List<FormacaoAcademica> formacoes;
	private List<Profissao> profissoes;
	private List<Projeto> projetos;
	private List<Cat> cat;

	public ConsultorDTO() {
	}

	public ConsultorDTO(Long id, String cpf, String cnpj, String nome, String telefone, String email,
			String dataNascimentoString, Cidade cidade) {
		this.id = id;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimentoString = dataNascimentoString;
		this.cidade = cidade;
	}

	public ConsultorDTO(Consultor consultor) {
		id = consultor.getId();
		cpf = consultor.getCpf();
		cnpj = consultor.getCnpj();
		nome = consultor.getNome();
		telefone = consultor.getTelefone();
		email = consultor.getEmail();
		dataNascimentoString = consultor.getDataNascimento().toString();
		idade = consultor.getIdade();
		cidade = consultor.getCidade();
		formacoes = consultor.getFormacao();
		profissoes = consultor.getProfissao();
		projetos = consultor.getProjeto();
		cat = consultor.getCat();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimentoString() {
		return dataNascimentoString;
	}

	public void setDataNascimentoString(String dataNascimentoString) {
		this.dataNascimentoString = dataNascimentoString;
	}

	public Integer getIdade() {
		return idade;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<FormacaoAcademica> getFormacoes() {
		return formacoes;
	}

	public List<Profissao> getProfissoes() {
		return profissoes;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public List<Cat> getCat() {
		return cat;
	}
}
