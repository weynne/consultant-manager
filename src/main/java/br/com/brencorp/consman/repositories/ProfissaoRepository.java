package br.com.brencorp.consman.repositories;

import br.com.brencorp.consman.entities.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {

    @Query("SELECT p FROM Profissao p " +
            "WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Profissao> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT p FROM Profissao p " +
            "WHERE LOWER(p.area) LIKE LOWER(CONCAT('%', :area, '%'))")
    List<Profissao> findByAreaContainingIgnoreCase(String area);

}