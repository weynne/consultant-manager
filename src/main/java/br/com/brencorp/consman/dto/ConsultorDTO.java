package br.com.brencorp.consman.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.brencorp.consman.entities.Cat;
import br.com.brencorp.consman.entities.Cidade;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.entities.Profissao;
import br.com.brencorp.consman.entities.Projeto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ConsultorDTO {

	private Long id;
	
	@Pattern(regexp = "[0-9]{11}", message = "Formato de CPF inválido")
	private String cpf;
	
	@Pattern(regexp = "[0-9]{14}", message = "Formato de CNPJ inválido")
	private String cnpj;
	
	@NotBlank(message = "Nome do consultor é obrigatório")
	private String nome;
	
	@Pattern(regexp = "[0-9]{11}", message = "Formato de telefone inválido")
	private String telefone;
	
	@Email(message = "E-mail inválido")
	private String email;
	
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private Integer idade;
	
	private Cidade cidade;

	private List<FormacaoAcademica> formacoes;
	
	private List<Profissao> profissoes;
	
	private List<Projeto> projetos;
	
	private List<Cat> cat;

	public ConsultorDTO() {
	}

	public ConsultorDTO(Long id, String cpf, String cnpj, String nome, String telefone, String email,
			LocalDate dataNascimento, Cidade cidade) {
		this.id = id;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cidade = cidade;
	}

	public ConsultorDTO(Consultor consultor) {
		id = consultor.getId();
		cpf = consultor.getCpf();
		cnpj = consultor.getCnpj();
		nome = consultor.getNome();
		telefone = consultor.getTelefone();
		email = consultor.getEmail();
		dataNascimento = consultor.getDataNascimento();
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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
