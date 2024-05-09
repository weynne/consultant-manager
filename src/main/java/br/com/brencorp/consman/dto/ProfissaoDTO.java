package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Profissao;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ProfissaoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank(message = "Nome da profissão é obrigatório.")
    private String nome;
    @NotBlank(message = "Área de atuação é obrigatório.")
    private String area;

    public ProfissaoDTO(Profissao profissao) {
        id = profissao.getId();
        nome = profissao.getNome();
        area = profissao.getArea();
    }
}