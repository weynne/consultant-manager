package br.com.brencorp.consman.dto;

import br.com.brencorp.consman.entities.Estado;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class EstadoDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Setter(AccessLevel.NONE)
	private Long id;
	@NotBlank(message = "Sigla do estado é obrigatório")
	@Pattern(regexp = "[A-Z]{2}", message = "Formato do estado inválido. Exemplo válido: PE")
	@Column(unique = true)
	private String uf;

	public EstadoDTO(Estado estado) {
		id = estado.getId();
		uf = estado.getUf();
	}
}
