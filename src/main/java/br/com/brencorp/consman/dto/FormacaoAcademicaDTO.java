package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.FormacaoAcademica;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class FormacaoAcademicaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank(message = "Nome do curso é obrigatório.")
    private String nome;
    @NotBlank(message = "Nome da instituição é obrigatório.")
    private String instituicao;
    @NotBlank(message = "Tipo do curso é obrigatório. Ex: Técnologo, Bacharelado, Mestrado")
    private String tipo;
    @NotNull(message = "Ano de conclusão do curso é obrigatório.")
    private Integer anoConclusao;
    @Transient
    private Integer tempoFormacao;

    public FormacaoAcademicaDTO(FormacaoAcademica formacaoAcademica) {
        id = formacaoAcademica.getId();
        nome = formacaoAcademica.getNome();
        instituicao = formacaoAcademica.getInstituicao();
        tipo = formacaoAcademica.getTipo();
        anoConclusao = formacaoAcademica.getAnoConclusao();
        tempoFormacao = formacaoAcademica.getTempoFormacao();
    }
}
