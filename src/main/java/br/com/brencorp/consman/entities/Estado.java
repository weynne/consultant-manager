package br.com.brencorp.consman.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estado")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Estado implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank(message = "Sigla do estado é obrigatório")
    @Pattern(regexp = "[A-Z]{2}", message = "Formato do estado inválido. Exemplo válido: PE")
    @Column(unique = true)
    private String uf;
    @JsonIgnore
    @OneToMany(mappedBy = "estado")
    @Setter(AccessLevel.NONE)
    private List<Cidade> cidades = new ArrayList<>();

    public Estado(String uf) {
        this.uf = uf;
    }
}