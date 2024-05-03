package br.com.brencorp.consman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brencorp.consman.entities.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
