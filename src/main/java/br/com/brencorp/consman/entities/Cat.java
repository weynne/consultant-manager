package br.com.brencorp.consman.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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