package br.com.brencorp.consman.repositories;

import br.com.brencorp.consman.entities.Consultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultorRepository extends JpaRepository<Consultor, Long> {

    @Query("SELECT c FROM Consultor c "
            + "WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Consultor> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT c FROM Consultor c "
            + "JOIN FETCH c.cidade ci "
            + "WHERE LOWER(ci.nome) LIKE LOWER(CONCAT('%', :cidade, '%'))")
    List<Consultor> findByCidadeContainingIgnoreCase(String cidade);

    @Query("SELECT c FROM Consultor c " +
            "JOIN FETCH c.cidade ci " +
            "JOIN FETCH ci.estado es " +
            "WHERE LOWER(es.uf) LIKE LOWER(CONCAT('%', :estado, '%'))")
    List<Consultor> findByEstadoContainingIgnoreCase(String estado);

    @Query("SELECT c FROM Consultor c "
            + "JOIN FETCH c.formacoes fo "
            + "WHERE LOWER(fo.nome) LIKE LOWER(CONCAT('%', :formacao, '%'))")
    List<Consultor> findByFormacaoContainingIgnoreCase(String formacao);

    @Query("SELECT c FROM Consultor c "
            + "JOIN FETCH c.formacoes fo "
            + "WHERE fo.anoConclusao BETWEEN :anoInicio AND :anoFim")
    List<Consultor> findByAnoConclusaoBetween(Integer anoInicio, Integer anoFim);

    @Query("SELECT c FROM Consultor c "
            + "WHERE YEAR(CURRENT_DATE) - YEAR(c.dataNascimento) BETWEEN :idadeMinima AND :idadeMaxima")
    List<Consultor> findByIdade(Integer idadeMinima, Integer idadeMaxima);

}