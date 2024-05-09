package br.com.brencorp.consman.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "profissao")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profissao implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;
	@NotBlank(message = "Nome da profissão é obrigatório.")
	private String nome;
	@NotBlank(message = "Área de atuação é obrigatório.")
	private String area;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "profissoes")
	@Setter(AccessLevel.NONE)
	private List<Consultor> consultores = new ArrayList<>();

	public Profissao(String nome, String area) {
		this.nome = nome;
		this.area = area;
	}
}
