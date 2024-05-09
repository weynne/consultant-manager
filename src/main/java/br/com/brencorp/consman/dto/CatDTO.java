package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Cat;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CatDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Setter(AccessLevel.NONE)
	private Long id;
	private String descricao;

	public CatDTO(Cat cat) {
		id = cat.getId();
		descricao = cat.getDescricao();
	}
}
