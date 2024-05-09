package br.com.brencorp.consman.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
