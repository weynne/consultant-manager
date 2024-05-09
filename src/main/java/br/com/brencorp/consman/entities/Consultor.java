package br.com.brencorp.consman.entities;

import br.com.brencorp.consman.utils.ConverteData;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Consultor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Pattern(regexp = "\\d{11}", message = "Formato de CPF inválido")
    private String cpf;
    @Pattern(regexp = "\\d{14}", message = "Formato de CNPJ inválido")
    private String cnpj;
    @NotBlank(message = "Nome do consultor é obrigatório")
    private String nome;
    @Pattern(regexp = "\\d{11}", message = "Formato de telefone inválido")
    private String telefone;
    @Email(message = "E-mail inválido")
    private String email;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @Transient
    @Setter(AccessLevel.NONE)
    private Integer idade;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
    @Setter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "consultor_formacao", joinColumns = @JoinColumn(name = "consultor_id"), inverseJoinColumns = @JoinColumn(name = "formacao_id"))
    private final List<FormacaoAcademica> formacoes = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "consultor_profissao", joinColumns = @JoinColumn(name = "consultor_id"), inverseJoinColumns = @JoinColumn(name = "profissao_id"))
    private final List<Profissao> profissoes = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "consultor_projetos", joinColumns = @JoinColumn(name = "consultor_id"), inverseJoinColumns = @JoinColumn(name = "projeto_id"))
    private final List<Projeto> projetos = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "consultor_cat", joinColumns = @JoinColumn(name = "consultor_id"), inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private final List<Cat> cat = new ArrayList<>();

    public Consultor(String cpf, String cnpj, String nome, String telefone, String email,
                     LocalDate dataNascimento, Cidade cidade) {
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cidade = cidade;
    }

    @Transient
    public Integer getIdade() {
        return ConverteData.periodoLocalDate(this.dataNascimento);
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
}