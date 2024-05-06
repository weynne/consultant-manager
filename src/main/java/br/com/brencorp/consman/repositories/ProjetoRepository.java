package br.com.brencorp.consman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brencorp.consman.entities.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
	
	List<Projeto> findByNomeContainingIgnoreCase(String nome);

}
