package br.com.brencorp.consman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brencorp.consman.entities.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	List<Cidade> findByNomeContainingIgnoreCase(String nome);

}
