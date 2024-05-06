package br.com.brencorp.consman.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.brencorp.consman.util.date.ConverteData;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "consultor")
public class Consultor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@Transient
	private Integer idade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "consultor_formacao", joinColumns = @JoinColumn(name = "consultor_id"), inverseJoinColumns = @JoinColumn(name = "formacao_id"))
	private List<FormacaoAcademica> formacoes = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "consultor_profissao", joinColumns = @JoinColumn(name = "consultor_id"), inverseJoinColumns = @JoinColumn(name = "profissao_id"))
	private List<Profissao> profissoes = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "consultor_projetos", joinColumns = @JoinColumn(name = "consultor_id"), inverseJoinColumns = @JoinColumn(name = "projeto_id"))
	private List<Projeto> projetos = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "consultor_cat", joinColumns = @JoinColumn(name = "consultor_id"), inverseJoinColumns = @JoinColumn(name = "cat_id"))
	private List<Cat> cat = new ArrayList<>();

	public Consultor() {
	}

	public Consultor(Long id, String cpf, String cnpj, String nome, String telefone, String email,
			LocalDate dataNascimento, Cidade cidade) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cidade = cidade;
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
		return ConverteData.periodoLocalDate(this.dataNascimento);
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<FormacaoAcademica> getFormacao() {
		return formacoes;
	}

	public List<Profissao> getProfissao() {
		return profissoes;
	}

	public List<Projeto> getProjeto() {
		return projetos;
	}

	public List<Cat> getCat() {
		return cat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consultor other = (Consultor) obj;
		return Objects.equals(id, other.id);
	}
}
