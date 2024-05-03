package br.com.brencorp.consman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brencorp.consman.entities.Profissao;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {
	
	List<Profissao> findByNomeContainingIgnoreCase(String nome);
	
	List<Profissao> findByAreaContainingIgnoreCase(String area);

}
