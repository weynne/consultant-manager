package br.com.brencorp.consman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brencorp.consman.entities.Profissao;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {

}
