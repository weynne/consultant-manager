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
import lombok.*;

@Entity
@Table(name = "cat")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cat implements Serializable {
	@Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
    private Long id;
    private String descricao;

	public Cat(String descricao) {
		this.descricao = descricao;
	}

	@JsonIgnore
    @ManyToMany(mappedBy = "cat")
    @Setter(AccessLevel.NONE)
    private List<Consultor> consultores = new ArrayList<>();
}