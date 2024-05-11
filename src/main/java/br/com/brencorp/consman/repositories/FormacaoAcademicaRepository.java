package br.com.brencorp.consman.repositories;

import br.com.brencorp.consman.entities.FormacaoAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormacaoAcademicaRepository extends JpaRepository<FormacaoAcademica, Long> {

    @Query("SELECT f FROM FormacaoAcademica f "
            + "WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<FormacaoAcademica> findByNome(String nome);

    @Query("SELECT f FROM FormacaoAcademica f "
            + "WHERE LOWER(f.instituicao) LIKE LOWER(CONCAT('%', :instituicao, '%'))")
    List<FormacaoAcademica> findByInstituicao(String instituicao);

    @Query("SELECT f FROM FormacaoAcademica f "
            + "WHERE LOWER(f.tipo) LIKE LOWER(CONCAT('%', :tipo, '%'))")
    List<FormacaoAcademica> findByTipo(String tipo);

    @Query("SELECT f FROM FormacaoAcademica f "
            + "WHERE f.anoConclusao BETWEEN :anoInicio AND :anoFim")
    List<FormacaoAcademica> findByAnoConclusao(Integer anoInicio, Integer anoFim);
}