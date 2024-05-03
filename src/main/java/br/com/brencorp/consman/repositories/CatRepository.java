package br.com.brencorp.consman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brencorp.consman.entities.Cat;

public interface CatRepository extends JpaRepository<Cat, Long> {

}
