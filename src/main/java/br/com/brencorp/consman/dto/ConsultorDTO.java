package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ConsultorDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @Transient
    @Setter(AccessLevel.NONE)
    private Integer idade;
    private Cidade cidade;
    @Setter(AccessLevel.NONE)
    private List<FormacaoAcademica> formacoes;
    @Setter(AccessLevel.NONE)
    private List<Profissao> profissoes;
    @Setter(AccessLevel.NONE)
    private List<Projeto> projetos;
    @Setter(AccessLevel.NONE)
    private List<Cat> cat;

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
}
