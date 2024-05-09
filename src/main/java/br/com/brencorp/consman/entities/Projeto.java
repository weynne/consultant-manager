package br.com.brencorp.consman.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projeto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;
	private String nome;
	@JsonIgnore
	@ManyToMany(mappedBy = "projetos")
	@Setter(AccessLevel.NONE)
	private List<Consultor> consultores = new ArrayList<>();

	public Projeto(String nome) {
		this.nome = nome;
	}
}
