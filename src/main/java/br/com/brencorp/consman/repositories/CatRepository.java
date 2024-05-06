package br.com.brencorp.consman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brencorp.consman.entities.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

}
