package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Projeto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ProjetoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    private Long id;
    private String nome;

    public ProjetoDTO(Projeto projeto) {
        id = projeto.getId();
        nome = projeto.getNome();
    }
}
