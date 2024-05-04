package br.com.brencorp.consman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brencorp.consman.entities.FormacaoAcademica;

@Repository
public interface FormacaoAcademicaRepository extends JpaRepository<FormacaoAcademica, Long> {

	List<FormacaoAcademica> findByNomeContainingIgnoreCase(String nome);
	
	List<FormacaoAcademica> findByInstituicaoContainingIgnoreCase(String instituicao);
	
	List<FormacaoAcademica> findByTipoContainingIgnoreCase(String tipo);
	
	List<FormacaoAcademica> findByAnoConclusaoContainingIgnoreCase(String anoConclusao);
}
