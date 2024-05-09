package br.com.brencorp.consman.entities;

import br.com.brencorp.consman.utils.ConverteData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "formacaoAcademica")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormacaoAcademica implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Setter(AccessLevel.NONE)
    private Integer tempoFormacao;
    @JsonIgnore
    @ManyToMany(mappedBy = "formacoes")
    @Setter(AccessLevel.NONE)
    private List<Consultor> consultores = new ArrayList<>();

    public FormacaoAcademica(String nome, String instituicao, String tipo, Integer anoConclusao) {
        this.nome = nome;
        this.instituicao = instituicao;
        this.tipo = tipo;
        this.anoConclusao = anoConclusao;
    }

    @Transient
    public Integer getTempoFormacao() {
        return ConverteData.periodoInteger(this.anoConclusao);
    }
}
