package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Cidade;
import br.com.brencorp.consman.entities.Estado;
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
public class CidadeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank(message = "Nome da cidade é obrigatório")
    private String nome;
    private Estado estado;

    public CidadeDTO(Cidade cidade) {
        id = cidade.getId();
        nome = cidade.getNome();
        estado = cidade.getEstado();
    }
}
