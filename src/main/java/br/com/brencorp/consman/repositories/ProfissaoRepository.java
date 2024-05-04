package br.com.brencorp.consman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brencorp.consman.entities.Profissao;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {
	
	List<Profissao> findByNomeContainingIgnoreCase(String nome);
	
	List<Profissao> findByAreaContainingIgnoreCase(String area);

}
